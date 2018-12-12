import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GameStart extends Application {

	private Screen startScreen; // the start screen
	private BorderPane basePane; // the basepane of the overall game -- allows changing screens
	private static BorderPane startPane;
	private static BorderPane helpPane;
	private static BorderPane gamePane;
	private static HBox startButtonPane = new HBox();
	private static HBox cardButtonPane = new HBox();
	private static HBox cardPane = new HBox();
	private static GridPane actionButtonPane = new GridPane();
	private static Text rules = new Text();
	private static Text header = new Text();
	private final float WINDOW_WIDTH = 1000; // instanciates width and height so i dont need to remember it later
	private final float WINDOW_HEIGHT = 750;
	private boolean cardsClickable = false;
	private boolean playingGame = false;

	private int turnCount = 0;
	/*
	 * private static Image cardImg; private static ImageView cardView;
	 */

	private static ArrayList<Image> cardImage = new ArrayList<>();
	private static ArrayList<ImageView> cardView = new ArrayList<>();

	private Button startButton, helpButton, toStartScreenButton, playCard, discardTrack, discardCard, forfeitGame;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		startScreen(primaryStage);
	}

	private void startScreen(Stage primaryStage) {
		// TODO Auto-generated method stub
		primaryStage.setTitle("CARAVAN: THE GAME");
		Button startButton = new Button("PLAY");
		Button helpButton = new Button("HELP");

		basePane = new BorderPane(); // makes the base pane
		startPane = new BorderPane(); // makes the start screen pane
		basePane.setCenter(startPane); // puts start pane in the center of base pane

		startButton.setScaleX(2); // resize button
		startButton.setScaleY(2);
		helpButton.setScaleX(2); // resize button
		helpButton.setScaleY(2);

		BorderPane.setAlignment(startButton, Pos.CENTER); // add and align button in start pane
		startPane.setBottom(startButtonPane);
		startButtonPane.getChildren().add(startButton);
		startButtonPane.getChildren().add(helpButton);

		startButtonPane.setAlignment(Pos.CENTER); // format button pane
		HBox.setMargin(startButton, new Insets(40, 40, 40, 40));
		HBox.setMargin(helpButton, new Insets(40, 40, 40, 40));

		startPane.setId("pane"); // set css stuff
		BorderPane.setMargin(startButton, new Insets(30, 30, 60, 30)); // set margins for button
		Scene scene = new Scene(basePane, WINDOW_WIDTH, WINDOW_HEIGHT, Color.BLACK);
		primaryStage.setMaxHeight(WINDOW_HEIGHT);
		primaryStage.setMaxWidth(WINDOW_WIDTH);
		scene.getStylesheets().addAll(this.getClass().getResource("styleCaravan.css").toExternalForm()); // get css
																											// information

		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				basePane.getChildren().remove(startPane);
				startButtonPane.getChildren().remove(startButton);
				startButtonPane.getChildren().remove(helpButton);
				startGame();
			}
		});

		helpButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				basePane.getChildren().remove(startPane);
				startButtonPane.getChildren().remove(startButton);
				startButtonPane.getChildren().remove(helpButton);
				helpScreen(primaryStage);
			}
		});

		primaryStage.setScene(scene); // set scene
		primaryStage.show();
	}

	public void helpScreen(Stage primaryStage) {
		helpPane = new BorderPane();
		basePane.setCenter(helpPane);

		Button toStartScreenButton = new Button("BACK");

		toStartScreenButton.setScaleX(2); // resize button
		toStartScreenButton.setScaleY(2);

		rules = new Text();
		rules.setFont(Font.font("Courier New", 14));
		rules.setText(
				"Caravan is played with two players building three opposing piles (or \"caravans\") of numbered cards. \n"
						+ "The goal is to outbid your opponent's caravan with the highest value of numbered cards without being too light\n"
						+ "(under 21) or overburdened (over 26).\r\n\n"
						+ "The game begins with each player taking eight cards from their deck and placing either one numerical card or\n"
						+ "ace on each caravan. Players may not discard during this initial round.\r\n\n"
						+ "Once both players have started their three caravans, each player may do ONE of the following on their turn:\r\n\n"
						+ "1. Play one card and draw a new card from his or her deck to their hand.\r\n"
						+ "2. Discard one card from their hand and draw a new card from his or her deck.\r\n"
						+ "3. Disband one of their three caravans by removing all cards from that pile.\r\n\n"
						+ "Caravans have a direction, either ascending or descending numerically, and a suit. The suit is determined with\n"
						+ "the first card placed on a caravan, the direction by the second. All subsequent cards must continue the\n"
						+ "numerical direction or match the suit of the previous card. Cards of the same numerical value cannot be\n"
						+ "played in sequence, regardless of suit. Face cards can be attached to numeric cards in any caravan and affects\n"
						+ "them in various ways.\r\n\n"
						+ "A player's caravan is considered sold when the value of its cards is over 20 and under 27. The other player may\n"
						+ "still outbid by increasing the value of their opposing pile while staying within the 21-26 range. When each\n"
						+ "of the three competing caravans has sold, the game is over. In the event that one of the three caravan values\n"
						+ "are tied between players, the game continues until all three caravans have sold. The player with two or more"
						+ "\nsales wins the pot.\r\n\n" + "CARD VALUES\r\n\n" + "Ace: Value of 1. \r\n"
						+ "2-10: Listed value. \r\n"
						+ "Jack: Played against ace, 2-10. Removes that card, along with any face cards attached to it.\r\n"
						+ "Queen: Played against ace, 2-10. Reverses the current direction of the hand and changes the current suit of the\n"
						+ "hand. Multiple queens may be played on the same card.\r\n"
						+ "King: Played against ace, 2-10. Adds the value of that card again. E.g. a king played on a 9 adds 9 to that hand.\n"
						+ "Multiple kings may be played on the same card for multiplicative effects. E.g. 4+ king = 8. 4 + 2 kings = 16.\r\n"
						+ "");

		BorderPane.setAlignment(toStartScreenButton, Pos.CENTER);
		helpPane.setBottom(toStartScreenButton);
		helpPane.setCenter(rules);
		helpPane.setId("help");
		BorderPane.setMargin(toStartScreenButton, new Insets(30, 30, 30, 30)); // set margins for button

		header = new Text(); // make location text
		BorderPane.setAlignment(header, Pos.CENTER);
		header.setText("RULES OF CARAVAN"); // setting the text and stuff
		header.setFont(Font.font("Rockwell", FontWeight.BOLD, 30));
		header.setFill(Color.RED);
		BorderPane.setMargin(header, new Insets(10, 10, 10, 10));

		helpPane.setTop(header);

		toStartScreenButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				basePane.getChildren().remove(startPane);
				startScreen(primaryStage);
			}
		});
	}

	public void startGame() {
		// START THE ACTUAL GAME
		turnCount = 1;

		gamePane = new BorderPane();
		basePane.setCenter(gamePane);

		gamePane.setTop(header);
		BorderPane.setAlignment(header, Pos.CENTER);
		header.setText("CLICK ON AN ACTION");
		header.setFont(Font.font("Rockwell", 20));
		header.setFill(Color.BLACK);
		BorderPane.setMargin(header, new Insets(10, 10, 10, 10));

		gamePane.setBottom(cardButtonPane);
		cardButtonPane.getChildren().add(actionButtonPane);
		cardButtonPane.getChildren().add(cardPane);

		actionButtonPane.setStyle("-fx-border-color: black");
		actionButtonPane.setPrefHeight(200);
		actionButtonPane.setPrefWidth(300);
		actionButtonPane.setAlignment(Pos.CENTER);

		cardPane.setStyle("-fx-border-color: black");
		cardPane.setPrefHeight(200);
		cardPane.setPrefWidth(700);
		cardPane.setAlignment(Pos.CENTER);

		Button playCard = new Button("PLACE\nCARD");
		Button discardTrack = new Button("DISCARD\nTRACK");
		Button discardCard = new Button("DISCARD");
		Button forfeitGame = new Button("FORFEIT");

		playCard.setScaleX(1.2);
		playCard.setScaleY(1.2);
		discardTrack.setScaleX(1.2);
		discardTrack.setScaleY(1.2);
		discardCard.setScaleX(1.2);
		discardCard.setScaleY(1.2);
		forfeitGame.setScaleX(1.2);
		forfeitGame.setScaleY(1.2);

		GridPane.setMargin(playCard, new Insets(10, 10, 10, 10));
		GridPane.setMargin(discardTrack, new Insets(10, 10, 10, 10));
		GridPane.setMargin(discardCard, new Insets(10, 10, 10, 10));
		GridPane.setMargin(forfeitGame, new Insets(10, 10, 10, 10));

		actionButtonPane.add(playCard, 0, 0);
		actionButtonPane.add(discardTrack, 1, 0);
		actionButtonPane.add(discardCard, 0, 1);
		actionButtonPane.add(forfeitGame, 1, 1);

		Hand hand = new Hand();
		System.out.print(hand);

		// displays the first hand on the game and adds cards to the arraylist
		for (int i = 0; i < 8; i++) {
			Card test = hand.getCardAtIndex(i);
			cardImage.add(test.getCardImg());
			cardView.add(test.getCardImgView());
			cardPane.getChildren().add(cardView.get(i));
			cardView.get(i).setFitHeight(132);
			cardView.get(i).setFitWidth(87);
			HBox.setMargin(cardView.get(i), new Insets(5, 5, 5, 5));
		}

		discardCard.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (!cardsClickable) {
					header.setText("CLICK ON A CARD TO DISCARD IT");
					cardsClickable = true;
					for (int i = 0; i < cardImage.size(); i++) {
						int num = i;
						cardView.get(num).setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent arg0) {
								if (cardsClickable) {
									if (turnCount > 3 && hand.getDeck().cardsLeft() > 0) {
										hand.drawCard();
										cardImage.add(hand.getCardAtIndex(hand.getHandSize() - 1).getCardImg());
										cardView.add(hand.getCardAtIndex(hand.getHandSize() - 1).getCardImgView());
										cardPane.getChildren().add(cardView.get(hand.getHandSize() - 1));
										cardView.get(hand.getHandSize() - 1).setFitHeight(132);
										cardView.get(hand.getHandSize() - 1).setFitWidth(87);
										HBox.setMargin(cardView.get(hand.getHandSize() - 1), new Insets(5, 5, 5, 5));
									}
									hand.discardCard(num);
									cardPane.getChildren().remove(cardView.get(num));
									
									header.setText("CLICK ON AN ACTION");
									turnCount++;
									cardsClickable = false;
									
									cardView.remove(num);
									cardImage.remove(num);
									
									showCardHand(hand);
								}
							}
						});
					}
				}
			}
		});

		forfeitGame.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Stage areYouSure = new Stage();
				areYouSure.initModality(Modality.APPLICATION_MODAL);
				areYouSure.setTitle("!");
				VBox dialogVbox = new VBox(12);
				Text t = new Text("Are you sure you want to forfeit?");
				t.setFont(Font.font(12));
				dialogVbox.getChildren().add(t);
				dialogVbox.setAlignment(Pos.CENTER);
				HBox yesNoButton = new HBox();
				Button buttonYes = new Button("YES");
				Button buttonNo = new Button("NO");

				buttonYes.setScaleX(1.2);
				buttonYes.setScaleY(1.2);
				buttonNo.setScaleX(1.2);
				buttonNo.setScaleY(1.2);

				HBox.setMargin(buttonYes, new Insets(10, 10, 10, 10));
				HBox.setMargin(buttonNo, new Insets(10, 10, 10, 10));

				dialogVbox.getChildren().add(yesNoButton);
				yesNoButton.setAlignment(Pos.CENTER);
				yesNoButton.getChildren().addAll(buttonYes, buttonNo);
				Scene dialogScene = new Scene(dialogVbox, 200, 100);
				areYouSure.setScene(dialogScene);

				buttonYes.setOnAction(e -> System.exit(1));
				buttonNo.setOnAction(e -> areYouSure.close());

				areYouSure.show();
			}
		});
	}

	public static void showCardHand(Hand hand){
		cardPane.getChildren().clear();
		for (int i = 0; i < cardView.size(); i++) {
			Card test = hand.getCardAtIndex(i);
			if(!cardImage.contains(test.getCardImg()))
				cardImage.add(test.getCardImg());
			if(!cardView.contains(test.getCardImgView()))
				cardView.add(test.getCardImgView());
			cardPane.getChildren().add(cardView.get(i));
			cardView.get(i).setFitHeight(132);
			cardView.get(i).setFitWidth(87);
			HBox.setMargin(cardView.get(i), new Insets(5, 5, 5, 5));
		}
	}
}
