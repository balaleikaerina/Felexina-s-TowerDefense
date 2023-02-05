package application.launcher.levels.level1;

import application.gamelogic.Game;
import application.gamelogic.enemy.AxeEnemy;
import application.gamelogic.enemy.ForkEnemy;
import application.gamelogic.enemy.SwordEnemy;
import application.gamelogic.tower.*;
import application.gui.EndScreenController;
import application.uicomponents.*;
import javafx.animation.AnimationTimer;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.util.LinkedList;

public class Level1Controller {
    private Level1View level1View;
    private LinkedList<Sprite> sprites = new LinkedList<>();
    private Game game = new Game(0);
    private LinkedList<double[]> enemyPath;

    public Level1Controller() {
        level1View = new Level1View();
        initialize();
    }

    public Level1View getMainView() {
        return level1View;
    }

    public void initialize() {
        File path = new File("src/resources/music/level0.mp3");
        Media media = new Media(path.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        level1View.setGoldLabel(game.getPlayerGold() + " Gold");

        level1View.getSpawnEnemy().setOnMouseClicked(event -> {
            game.spawnWave();
            //level1View.getSpawnEnemy().setOpacity(0);
        });

        game.isWaveFinishedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(t1) {
                    level1View.getSpawnEnemy().setMouseTransparent(false);
                } else {
                    level1View.getSpawnEnemy().setMouseTransparent(true);

                }
            }
        });

        level1View.getSpawnEnemy().setOnMouseClicked(event -> {
            game.spawnWave();
            //level1View.getSpawnEnemy().setOpacity(0);
        });

        /* Debug mouseposition label
        level1View.getClickRegisterPane().setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String posStr = "X: " + mouseEvent.getX() + " || Y: " + mouseEvent.getY();
                Platform.runLater(() -> {
                    level1View.getxAndYLabel().setText(posStr);
                });
            }
        });

         */



        level1View.getUpgradeTower().addEventHandler(ActionEvent.ACTION, event -> {
            if(level1View.setClickRegisterPaneTransparent()) {
                level1View.getUpgradeTower().setStyle("-fx-background-color: #32a852");
                for(Sprite sprite : sprites) {
                    if(sprite instanceof TowerSprite) {
                        ((TowerSprite) sprite).showUpgradeButton();
                    }
                }
            } else {
                level1View.getUpgradeTower().setStyle("-fx-background-color: transparent");
                for(Sprite sprite : sprites) {
                    if(sprite instanceof TowerSprite) {
                        ((TowerSprite) sprite).hideUpgradeButton();
                    }
                }
            }
        });

        level1View.getPlaceArcaneTower().setOnMouseClicked(mouseEvent -> {
            game.setTowerChooser("ArcaneTower");
            System.out.println(game.getTowerChooser());
        });

        level1View.getPlaceArcherTower().setOnMouseClicked(mouseEvent -> {
            game.setTowerChooser("ArcherTower");
        });

        level1View.getPlaceCannonTower().setOnMouseClicked(mouseEvent -> {
            game.setTowerChooser("CannonTower");
        });

        level1View.getPlaceFireTower().setOnMouseClicked(mouseEvent -> {
            game.setTowerChooser("FireTower");
        });

        level1View.getClickRegisterPane().setOnMouseClicked(event -> {
            //System.out.println("X: " + event.getX() + " || Y: " + event.getY());
            if(event.getButton() == MouseButton.PRIMARY) {

                Tower tower;
                TowerSprite towerSprite;

                switch (game.getTowerChooser()) {
                    case "ArcaneTower":
                        tower = new ArcaneTower(event.getX(), event.getY(), game.getListOfEnemies());
                        towerSprite = new ArcaneTowerSprite(tower);
                        break;
                    case "ArcherTower":
                        tower = new ArcherTower(event.getX(), event.getY(), game.getListOfEnemies());
                        towerSprite = new ArcherTowerSprite(tower);
                        break;
                    case "CannonTower":
                        tower = new CannonTower(event.getX(), event.getY(), game.getListOfEnemies());
                        towerSprite = new CannonTowerSprite(tower);
                        break;
                    case "FireTower":
                        tower = new FireTower(event.getX(), event.getY(), game.getListOfEnemies());
                        towerSprite = new FireTowerSprite(tower);
                        break;
                    default:
                        tower = new ArcaneTower(event.getX(), event.getY(), game.getListOfEnemies());
                        towerSprite = new ArcaneTowerSprite(tower);
                        break;
                }

                double[] positionToPlace = game.tryToPlaceTower(event.getX(), event.getY(), tower);


                if((positionToPlace != null) && game.enoughGoldForTower("testTower")) {
                    File shotPath = new File("src/resources/music/shot.mp3");
                    Media shotMedia = new Media(shotPath.toURI().toString());
                    MediaPlayer shotMediaPlayer = new MediaPlayer(shotMedia);
                    shotMediaPlayer.setVolume(0.25);

                    tower.setX(positionToPlace[0] + (game.getStep() / 2));
                    tower.setY(positionToPlace[1] + (game.getStep() / 2));


                    sprites.add(towerSprite);
                    level1View.getBaseAnchorPane().getChildren().add(towerSprite);

                    game.add(tower);

                    towerSprite.getUpgradeButton().addEventFilter(ActionEvent.ACTION, upgradeEvent -> {
                        if(tower.getLevel() == 1 && game.enoughGoldForTower("upgradeLvl1")) {
                            tower.upgrade();
                            towerSprite.setUpgradeButtonText(tower.getLevel(), tower.getMAXLEVEL());
                        } else  if(tower.getLevel() == 2 && game.enoughGoldForTower("upgradeLvl2")) {
                            tower.upgrade();
                            towerSprite.setUpgradeButtonText(tower.getLevel(), tower.getMAXLEVEL());
                        }
                        //TODO: hide button is maxlevel is reached
                    });

                    final BulletAnimation[] testBullet = new BulletAnimation[1];                    

                    tower.animationTriggerProperty().addListener(new ChangeListener<Boolean>() {
                        @Override
                        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                            shotMediaPlayer.stop();
                            shotMediaPlayer.play();
                            
                            testBullet[0] = null;
                            testBullet[0] = new BulletAnimation(tower, tower.getCurrentEnemyToAttack());
                            sprites.add(testBullet[0]);
                            Platform.runLater(() -> {
                                level1View.getBaseAnchorPane().getChildren().add(testBullet[0]);
                            });
                            testBullet[0].reachedDestinationProperty().addListener(new ChangeListener<Boolean>() {
                                @Override
                                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                                    sprites.remove(testBullet[0]);
                                    Platform.runLater(() -> {
                                        level1View.getBaseAnchorPane().getChildren().remove(testBullet[0]);
                                    });
                                }
                            });
                        }
                    });
                }
            }
        });

        level1View.getTowerRange().setOnMouseClicked(event -> {
            for(Sprite sprite : sprites) {
                if(sprite instanceof TowerSprite) {
                    ((TowerSprite) sprite).setRangeIndicator();
                }
            }
        });

        game.playerHPProperty().addListener((observableValue, oldValue, t1) -> {
            level1View.setHpStat("HP: 0" + t1.intValue());
            if(t1.intValue() == 0) {
                mediaPlayer.stop();

                EndScreenController controller = new EndScreenController();
                Scene scene = new Scene(controller.getEndScreen());

                controller.setHeaderImage(1);
                controller.setBackground(0);
                controller.setBackgroundBlur("e13333b5");
                controller.setEnemys_killed("You eliminated " + game.getKillCounter() + " enemies");
                controller.setHP_left("You had " + game.playerHPProperty().getValue() + " HP left");
                controller.setTowers_placed("You placed " + String.valueOf(game.getTotalTowers()) + " Towers");
                controller.startMusic();

                Stage window = (Stage) level1View.getScene().getWindow();
                window.setScene(scene);
                window.show();
            }
        });

        game.lastWaveProperty().addListener((observableValue, oldValue, t1) -> {
            if (t1.booleanValue()) {
                game.isWaveFinishedProperty().addListener((observableVal, oldVal, t) -> {
                    if(game.playerHPProperty().intValue() > 0) {
                        mediaPlayer.stop();
    
                        EndScreenController controller = new EndScreenController();
                        Scene scene = new Scene(controller.getEndScreen());
    
                        controller.setHeaderImage(0);
                        controller.setBackground(0);
                        controller.setBackgroundBlur("06a11282");
                        controller.setEnemys_killed("You eliminated " + game.getKillCounter() + " enemies");
                        controller.setHP_left("You had " + game.playerHPProperty().getValue() + " HP left");
                        controller.setTowers_placed("You placed " + String.valueOf(game.getTotalTowers()) + " Towers");
                        controller.startMusic();
    
                        Stage window = (Stage) level1View.getScene().getWindow();
                        window.setScene(scene);
                        window.show();
                    }
                });
            }
        });

        game.currentEnemyProperty().addListener((observableValue, enemy, t1) -> {
            EnemySprite testEnemy;
            if(t1 instanceof AxeEnemy) {
                testEnemy = new AxeEnemySprite();
            } else if (t1 instanceof SwordEnemy) {
                testEnemy = new SwordEnemySprite();
            } else if (t1 instanceof ForkEnemy) {
                testEnemy = new ForkEnemySprite();
            } else {
                testEnemy = new AxeEnemySprite();
            }
            testEnemy.setLayoutX(t1.getX());
            testEnemy.setLayoutY(t1.getY());
            testEnemy.gameObjectProperty().set(t1);
            sprites.add(testEnemy);
            Platform.runLater(() -> {
                level1View.getBaseAnchorPane().getChildren().add(testEnemy);
            });

            t1.finishedProperty().addListener((observableVal, aBoolean, t) -> {
                if(t.booleanValue()) {
                    game.failedWave();
                    level1View.getBaseAnchorPane().getChildren().remove(testEnemy);
                    sprites.remove(testEnemy);
                }
            });

            t1.aliveProperty().addListener((observableVal, aBoolean, t) -> {
                level1View.getBaseAnchorPane().getChildren().remove(testEnemy);
                sprites.remove(testEnemy);
            });
        });

        game.indexProperty().addListener((observableVal, aBoolean, t) -> {
            Platform.runLater(() -> {
                level1View.setWaveStat("Wave: " + t.intValue());
            });
        });

        game.playerGoldProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                Platform.runLater(() -> {
                    level1View.setGoldLabel(t1 + " Gold");
                });
            }
        });

        AnimationTimer animationThread = new AnimationTimer() {
            private long lastUpdated = 0;
            private long lastRendered = 0;
            private final int UPS = 60;
            private final int FPS = 60;

            private final int SECONDS2NANO_SECONDS = 1_000 * 1_000_000;
            private final int UPNS_DELTA = SECONDS2NANO_SECONDS / UPS;
            private final int FPNS_DELTA = SECONDS2NANO_SECONDS / FPS;

            @Override
            public void handle(long now) {
                if (lastUpdated + UPNS_DELTA < now) {
                    double delta = lastUpdated == 0 ? 0 : (now - lastUpdated) / (double)SECONDS2NANO_SECONDS;
                    game.update(delta);
                    lastUpdated = now;
                }

                if (lastRendered + FPNS_DELTA < now) {
                    double delta = lastRendered == 0 ? 0 : (now - lastRendered) / (double)SECONDS2NANO_SECONDS;
                    for(Sprite sprite : sprites) {
                        sprite.render();
                        sprite.render(delta);
                    }
                    lastRendered = now;
                }
            }
        };

        animationThread.start();
    }
}
