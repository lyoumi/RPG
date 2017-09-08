package game.controller;

import com.thoughtworks.xstream.XStream;
import ext.ExtendedText;
import game.model.Characters.Character;
import game.model.Characters.characterclasses.Archer;
import game.model.Characters.characterclasses.Berserk;
import game.model.Characters.characterclasses.Wizard;
import game.model.Items.Equipment;
import game.model.Items.EquipmentItems;
import game.model.Items.UsingItems;
import game.model.Items.itemsclasses.HealingItems;
import game.model.Items.itemsclasses.Item;
import game.model.Items.itemsclasses.healclasses.healHitPoint.items.BigHPBottle;
import game.model.Items.itemsclasses.healclasses.healHitPoint.items.MiddleHPBottle;
import game.model.Items.itemsclasses.healclasses.healHitPoint.items.SmallHPBottle;
import game.model.Items.itemsclasses.healclasses.healManaPoint.items.BigFlower;
import game.model.Items.itemsclasses.healclasses.healManaPoint.items.MiddleFlower;
import game.model.Items.itemsclasses.healclasses.healManaPoint.items.SmallFlower;
import game.model.Monsters.Monster;
import game.model.Monsters.equipment.equipmentclasses.SimpleMonsterEquipment;
import game.model.Monsters.monstersclasses.*;
import game.model.Quests.Quest;
import game.model.Quests.questsclasses.LegendaryQuest;
import game.model.abilities.Magic;
import game.model.abilities.MagicClasses;
import game.model.abilities.instants.instantmagics.healing.SmallHealing;
import game.model.abilities.magicStyle.Style;
import game.model.traders.Trader;
import game.model.traders.tradersclasses.SimpleTrader;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.print.PageLayout;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jdk.nashorn.internal.codegen.CompilerConstants;
import org.jasypt.util.text.BasicTextEncryptor;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by pikachu on 13.07.17.
 */
public class PlayerController {

    public HBox gameMenuBox;
    private boolean firstTime = true;
    private String choice;
    private boolean canTakeMessage;

    public CheckMenuItem slowGameStyle;

    public Text caseFirst;
    public Text caseSecond;
    public Text caseThird;
    public Text caseFourth;
    public Text caseFifth;
    public Text caseSixth;

    public Text viewName;
    public Text viewClass;
    public Text viewLevel;
    public Text viewExp;
    public Text viewAttack;
    public Text viewHitPoint;
    public Text viewManaPoint;
    public Text viewGold;
    public Text viewQuest;
    public VBox messageBox;
    public VBox currentChoiceBox;
    public VBox itemBox;
    public VBox equipmentBox;
    public ScrollPane messageBoxScrollPane;

    private Character character;
    private Quest quest;
    private int task;
    private InnerPlayerControllerClass innerPlayerControllerClass;

    private String myEncryptionPassword = "7~e~~^~};&uF@8jgX2@~?_[4y4RN!,c`<y@3[.cK78}r~#B8wq7C8w/3cP)Gn+ENKf/Fd(!8fX#B3^YAJF37n!KY9/\\%A6P(`QY&YGhbEC*q=_[^p+M*Nvf<_4}n?>SU59a2#&kw~DVdfNt&#2\"'Jf;wkn$<pZ-:aA'&5A7qtkkvcyJ_G!)-~RXNuPEw$Rkr7x%pCnfgYyH}Z@t)E'(v,~VvGbya8%`Mv:@a<fT;aRZ%h~bm`A885?m'XNFY9eZ{n-D$3Nv$vsJNtThjdw)2gKYnApR>*vCPDp/&aj~$]Xdj7'}q[dx;X'tt\"=s*>+?EFg{?sHSbNG;{v8q(YQ}-Dc%g5{g3cZP/.[@s$3cu2;V8[Mu<*4pY(_3[W~@YX4rjqf,@w&BUV'u~~]^N?w~MargR-S>e]xkm!PD24_UJ;`ZT,bSD#'c5,cUT#nT:\\n7RgJpZ\"_%]nP)&7cHQ/pj6{D;j\"e9@5%n@v<d{zp,A3+nR9t^jE8H~X3GnZ7^?9:LX_]m{{~)}9@qY5HnmA@,gk}^%R[&/-Ywf+#;$;:=?xuB~EqB63:zTsrYXb6djWf?;\\}U%nQ%fue7*{#__Se3JK,t+F\\aCSc+D.^dS/h+`3t&DM^R3pfVh=rW9kkz@2V4Sg>8jbGJaS')`t%FeYAD.mE-&*!+C>u~}gQkzY^Y5Z]$geRS_FhASc.Q/cP8E6p[!<8fd6GQ;Q=YA7<N*4xX+C2jq3h4s^t%#9[2D^3{<(gx\\#'{Nf/uwV',9_xU[sg3@_Y*7tQjJr+xfpH}-gdb:9XDD@CAqk(T8.;AJ(E@rq,w5kL@`syZaA(aXWXS:!7qQcZ(`MRWBHdT;dgN_^X&}U[rW+wWd^tu+YBSWMZ_S9#Vp>@C?g`qcXU%,Hnk?^k,6F4_d<P='`9M#c=:)'$tc')#fLNX~>%,\"fPfYj,fh}_8\\z3~Gm%#r+J#Q*>(pL)gtUXj'S)$a;zgUazET[HM:%m3W:M7+B;E)A~WKNsR+yj.9bd}c.NgZ(kAd5$#?nhU\\!\"b&}W>^yy&<7L=9'*[FyNH^}8CB7GH--/e+bYv\\v\\NG$=St];@E2(a]^\\a\\:#~Vc6C&kYhWPF9B[w_;,Vj;Qd&fgX2PbWtk\\.xdf&e5p/QQ6\\y(`,HZ*9JR#mm(2RP8E=<:*j<'-r?p$Bn!sH:uH{?h?pwR[$f5-M6'PP#_9a\\G;=L=xD5>5=V!$7wq@3MSR/SL]b>;`\\!+uj#tLk=cF]H^:`m$`XF8Z!As.;YAd*TDrAjF)DGQL]z!w!8_@%b@YuHTSrU7RZ,u@z;&3z7h6qc_5>/:-VBmz^c:zeMrjbp6k9=)3wZk<6M4YV3R6\\&Rv\\?ebdyAW-y<BsmwVmPLUe,<Qd`<&RH$f,CM}H{\\UDcRJ@g>^rhAn:7J<X>:J8ba~m3>{tE}FGyB*3Zc)zW;]\"j%mXUZx<eLfG37d:'-Fp=Q&q\"t:z@![6su9aQWT&;jjV,h;X)8YTx{nk~aKcu't(C\\_.]^qS~vTjdw:$5x&}mtA/2XqBk8'WC*Ta/xQTmdAr/d2BZ~m!H@wZ*\"qp]ZEU:^@&uy?\"=4hv-!^AB5;M\\d[ye[t:)}wJBRQ*Qd{WS4ry\\)GUZR*^2Asu?hDyFJ&dDw@?\"VE9*f(R.@!D.txMJg7#hvBc<kKa4v{W33SvF\"eZxV~^xCM'mw\"KyEy[6h2R_yqr<8jw<ug``d4T!87)FN6`\"&t*7P\\=Jp!4/-rKJ5J_QqC*EJX!QM)Z>pFjhvZKGedb\\&EnZ?+X`B.H*2_jf9K3[7?&h+]%&&&?Wc(Y$)/jc6>z=Rz>G(,=J~?d\"xM:=k5=_f4)CE3uyAUv;S,)pyp;VL*2%=v\"T*n~r4b]dUYGL4.vDS:Q#hqj%=X!Qd8)m6@`Q{7NZvSqCcyw8[]dpLpemC'S8ruSZh85k+%`\"<p]zX-)8L+SLr&EK^Ausg,;sn-m5SwNE8AU*YF69JC%aka+LUGZu`J%V7AW'RPY!:U%Zsr2FH+3VND.KUy}%dAYdkVZ/UPnZ-gS6^#{+GaPYxxYk?2B)[fM.@Sv!S+X}F";

    public class InnerPlayerControllerClass implements Runnable {

        private Button useItemButton = new Button("Use item");
        private Button walkingButton = new Button("Walking");
        private Button autoBattleButton = new Button("Auto-battle");
        private Button manualBattleButton = new Button("Manual battle");
        private Button tavernButton = new Button("Tavern");
        private Button stopButton = new Button("Stop");

        private Button[] mainMenu = new Button[5];

        private Button breakButton = new Button("Break");
        private Button breakAutoBattleButton = new Button("Break");

        private Button[] autoBattleMenu = new Button[4];

        private Button tavernEquipmentButton = new Button("Equipment");
        private Button tavernHealingItemsButton = new Button("Healing items");
        private Button tavernQuestsButton = new Button("Quests");
        private Button tavernBuyEquipmentButton = new Button("Buy");
        private Button tavernBuyAllEquipmentButton = new Button("Buy all");
        private Button tavernBuyHealingItemsButton = new Button("Buy");
        private TextArea tavernFieldIdForBuyEquipmentItems = new TextArea();
        private TextArea tavernFieldIdForBuyHealingItems = new TextArea();
        private TextArea tavernFieldCountForBuyHealingItems = new TextArea();
        private Button tavernTradersQuest = new Button("Traders");
        private Button tavernLegendaryQuest = new Button("Legendary");
        private Button tavernExitFromEquipment = new Button("Exit");
        private Button tavernExitFromHealingItems = new Button("Exit");
        private Button tavernExitFromTavern = new Button("Exit");
        private Button tavernExitFromQuestTalking = new Button("Exit");

        private Control[] tavernMenu = new Control[4];
        private Control[] tavernEquipmentMenu = new Control[4];
        private Control[] tavernHealingItemsMenu = new Control[4];
        private Button[] tavernQuestMenu = new Button[3];

        private Button upgradeSkillFirst = new Button(Style.getMagicStyle(character).get(0).toString());
        private Button upgradeSkillSecond = new Button(Style.getMagicStyle(character).get(1).toString());
        private Button upgradeSkillThird = new Button(Style.getMagicStyle(character).get(2).toString());

        private Button[] skillButtons = new Button[3];
        private Button [] healingItemMenu = new Button[7];

        private Button useMagicButton = new Button("Use force");
        private Button continueButton = new Button("Continue");
        private Control[] manualBattleButtons = new Control[5];

        private final Random random = new Random();
        private final List<HealingItems> itemsList = SimpleMonsterEquipment.monsterEquipmentFactory.getMonsterEquipment().initializeItemList();
        private final int sizeOfItems = itemsList.size();

        private Monster aliveMonster = null;

        @Override
        public void run() {

            Button bigHitPointBottle = new Button("BigHitBottle");
            bigHitPointBottle.setOnAction(event -> {
                if (character.getCountOfBigHitPointBottle() > 0)((UsingItems) character).use(getBigHitPointBottle());
                updateScreen();
            });
            Button middleHitPointBottle = new Button("MiddleHitBottle");
            middleHitPointBottle.setOnAction(event -> {
                if (character.getCountOfMiddleHitPointBottle() > 0) character.use(getMiddleHitPointBottle());
                updateScreen();
            });
            Button smallHitPointBottle = new Button("SmallHitBottle");
            smallHitPointBottle.setOnAction(event -> {
                if (character.getCountOfSmallHitPointBottle() > 0)((UsingItems) character).use(getSmallHitPointBottle());
                updateScreen();
            });
            Button bigFlowerButton = new Button("BigFlower");
            bigFlowerButton.setOnAction(event -> {
                if (character.getCountOfBigFlower() > 0)((UsingItems) character).use(getBigFlower());
                updateScreen();
            });
            Button middleFlowerButton = new Button("MiddleFlower");
            middleFlowerButton.setOnAction(event -> {
                if (character.getCountOfMiddleFlower() > 0)((UsingItems) character).use(getMiddleFlower());
                updateScreen();
            });
            Button smallFlowerButton = new Button("SmallFlower");
            smallFlowerButton.setOnAction(event -> {
                if (character.getCountOfSmallFlower() > 0)((UsingItems) character).use(getSmallFlower());
                updateScreen();
            });
            Button backButton = new Button("Back");
            backButton.setOnAction(event -> nextChoice());

            healingItemMenu[0] = bigHitPointBottle;
            healingItemMenu[1] = middleHitPointBottle;
            healingItemMenu[2] = smallHitPointBottle;
            healingItemMenu[3] = bigFlowerButton;
            healingItemMenu[4] = middleFlowerButton;
            healingItemMenu[5] = smallFlowerButton;
            healingItemMenu[6] = backButton;


            Button firstSkillButton = new Button(Style.getMagicStyle(character).get(0).toString());
            associateButtonSkill(firstSkillButton, Style.getMagicStyle(character).get(0));
            Button secondSkillButton = new Button(Style.getMagicStyle(character).get(1).toString());
            associateButtonSkill(secondSkillButton, Style.getMagicStyle(character).get(1));
            Button thirdSkillButton = new Button(Style.getMagicStyle(character).get(2).toString());
            associateButtonSkill(thirdSkillButton, Style.getMagicStyle(character).get(2));

            useItemButton.setOnAction(event -> useItem(character));
            mainMenu[0] = useItemButton;
            walkingButton.setOnAction(event -> walking());
            mainMenu[1] = walkingButton;
            autoBattleButton.setOnAction(event -> autoBattle());
            mainMenu[2] = autoBattleButton;
            tavernButton.setOnAction(event -> tavern());
            mainMenu[3] = tavernButton;
            stopButton.setOnAction(event -> exit());
            mainMenu[4] = stopButton;

            manualBattleButtons[0] = useItemButton;
            useMagicButton.setOnAction(event -> useMagic(aliveMonster));
            manualBattleButtons[1] = useMagicButton;
            manualBattleButtons[2] = breakButton;
            manualBattleButtons[3] = continueButton;

            breakAutoBattleButton.setOnAction(event -> nextChoice());
            autoBattleMenu[0] = breakAutoBattleButton;
            autoBattleMenu[1] = firstSkillButton;
            autoBattleMenu[2] = secondSkillButton;
            autoBattleMenu[3] = thirdSkillButton;

            tavernMenu[0] = tavernEquipmentButton;
            tavernMenu[1] = tavernHealingItemsButton;
            tavernMenu[2] = tavernQuestsButton;
            tavernMenu[3] = tavernExitFromTavern;

            tavernEquipmentMenu[0] = tavernFieldIdForBuyEquipmentItems;
            tavernEquipmentMenu[0].setMaxSize(60, 30);
            tavernEquipmentMenu[0].setMinSize(60, 30);
            tavernEquipmentMenu[0].setPrefSize(60, 30);
            tavernEquipmentMenu[1] = tavernBuyEquipmentButton;
            tavernEquipmentMenu[2] = tavernBuyAllEquipmentButton;
            tavernEquipmentMenu[3] = tavernExitFromEquipment;

            tavernHealingItemsMenu[0] = tavernFieldIdForBuyHealingItems;
            tavernHealingItemsMenu[0].setMaxSize(60, 30);
            tavernHealingItemsMenu[0].setMinSize(60, 30);
            tavernHealingItemsMenu[0].setPrefSize(60, 30);
            tavernHealingItemsMenu[1] = tavernFieldCountForBuyHealingItems;
            tavernHealingItemsMenu[1].setMaxSize(60, 30);
            tavernHealingItemsMenu[1].setMinSize(60, 30);
            tavernHealingItemsMenu[1].setPrefSize(60, 30);
            tavernHealingItemsMenu[2] = tavernBuyHealingItemsButton;
            tavernHealingItemsMenu[3] = tavernExitFromHealingItems;

            tavernQuestMenu[0] = tavernTradersQuest;
            tavernQuestMenu[1] = tavernLegendaryQuest;
            tavernQuestMenu[2] = tavernExitFromQuestTalking;

            upgradeSkillFirst.setOnAction(event -> {
                Style.getMagicStyle(character).get(0).setDamage();
                character.setMagicPoint(character.getMagicPoint() - 1);
                Platform.runLater(() -> messageBox.getChildren().add(new Text(Style.getMagicStyle(character).get(0) + " was upgraded")));
            });
            upgradeSkillSecond.setOnAction(event -> {
                Style.getMagicStyle(character).get(1).setDamage();
                character.setMagicPoint(character.getMagicPoint() - 1);
                Platform.runLater(() -> messageBox.getChildren().add(new Text(Style.getMagicStyle(character).get(1) + " was upgraded")));
            });
            upgradeSkillThird.setOnAction(event -> {
                Style.getMagicStyle(character).get(0).setDamage();
                character.setMagicPoint(character.getMagicPoint() - 1);
                Platform.runLater(() -> messageBox.getChildren().add(new Text(Style.getMagicStyle(character).get(2) + " was upgraded")));
            });

            skillButtons[0] = upgradeSkillFirst;
            skillButtons[1] = upgradeSkillSecond;
            skillButtons[2] = upgradeSkillThird;

            updateScreen();
            nextChoice();
        }

        private void associateButtonSkill(Button magicButton, Magic magic) {
            if (Style.getMagicStyle(character).get(0).getMagicClass() == MagicClasses.COMBAT) {
                magicButton.setOnAction(event -> {
                    if (!Objects.equals(aliveMonster, null)) {
                        aliveMonster.setDebuff(magic);
                        aliveMonster.setHitPoint(aliveMonster.getHitPoint() - aliveMonster.applyDamage(character.useMagic(magic)));
                        ExtendedText extendedText = new ExtendedText("   info: " + aliveMonster.toString());
                        extendedText.setFill(Color.ORANGERED);
                        Platform.runLater(() -> messageBox.getChildren().add(extendedText));
                        updateScreen();
                    }
                });
            } else if (Style.getMagicStyle(character).get(0).getMagicClass() == MagicClasses.HEALING) {
                character.setHitPoint(character.getHitPoint() + character.useMagic(magic));
                updateScreen();
            } else {
                character.useMagic(magic);
                updateScreen();
            }

        }

        /**
         * Метод, описывающий возможный выбор по окончании ручного или автоматического боя, или же поиска ресурсов.
         *
         * @return boolean result
         */
        private boolean nextChoice() {
            Text choice = new Text("\n   info: What's next: use item for healHitPoint, walking for find new items, " +
                    "\n           auto-battle for check your fortune, tavern, \n           stop for break adventures or continue....\n");
            updateChoiceBox(" use item", " walking", " auto-battle", " tavern", " stop", " manual battle");
            Platform.runLater(() -> messageBox.getChildren().add(choice));
            updateGameMenu(mainMenu);
            return true;
        }

        private void updateGameMenu(Control... controls) {
            Platform.runLater(() -> gameMenuBox.getChildren().clear());
            for (Control control : controls) {
                Platform.runLater(() -> gameMenuBox.getChildren().add(control));
            }
        }

        /**
         * Режим автоматического ведения боя. В случае с малым количеством здоровья персонаж будет способен
         * самостоятельно восстановить здоровье, а в случае отсутствия предметов для восстановления
         * отправится в путешествие для их поиска (walking())
         */
        private void autoBattle() {
            updateChoiceBox(" break");
            updateGameMenu(autoBattleMenu);

            new Thread(() -> {
                battle:
                while (!breakAutoBattleButton.isPressed()) {
                    if (random.nextInt(10000000) == 9999999) {
                        Monster monster = spawn(character);
                        if (monster instanceof Boss) while (character.getHitPoint() < character.getMaxHitPoint())
                            if (!autoHeal()) break battle;
                        ExtendedText boss = new ExtendedText("   info: battle began with " + monster.toString());
                        boss.setFill(Color.ORANGERED);
                        Platform.runLater(() -> messageBox.getChildren().add(boss));
                        try {
                            boss.finalize();
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                        do {
                            autoHeal();
                            updateScreen();
                            if (!punch(monster)) break battle;
                            if (monster.isDead()) break;
                            if (character.getHitPoint() == 0) {
                                exit();
                                return;
                            }
                        } while (!breakAutoBattleButton.isPressed());
                        updateScreen();
                        endEvent(character, monster, true);
                        System.gc();
                        try {
                            monster.finalize();
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                        if (character.getMagicPoint() > 0) {
                            checkNewMagicPoint();
                            return;
                        }
                    }
                }

            }).start();
        }

        private void alertMessage(String message) {
            ExtendedText notCorrectId = new ExtendedText(message);
            notCorrectId.setFill(Color.RED);
            Platform.runLater(() -> messageBox.getChildren().add(notCorrectId));
            try {
                notCorrectId.finalize();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }

        /**
         * Метод, реализующий поведение торговца.
         */
        private void tavern() {
            Trader trader = SimpleTrader.tradersFactory.getTrader(character);
            updateChoiceBox(" equipment", " items", " quests", " exit");
            ExtendedText viewWelcomeSpeech = new ExtendedText("\n   info: Hello my friend! Look at my priceList or quests: enter equipmentclasses, item, quest or exit for exit from market....");
            viewWelcomeSpeech.setFill(Color.BLUEVIOLET);
            Platform.runLater(() -> messageBox.getChildren().add(viewWelcomeSpeech));
            try {
                viewWelcomeSpeech.finalize();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            updateGameMenu(tavernMenu);
            tavernEquipmentButton.setOnAction(event -> {
                updateGameMenu(tavernEquipmentMenu);
                for (Map.Entry<Integer, Item> entry :
                        trader.getPriceListEquipmentObjects().entrySet()) {
                    Platform.runLater(() -> messageBox.getChildren().add(new Text("   info: Price: " +
                            entry.getValue().getPrice() + "G - " + "id: " + entry.getKey() + "; " + entry.getValue())));
                }
                Platform.runLater(() -> messageBox.getChildren().add(new Text("   info: Pls, make your choice or enter 0 for exit....")));

                updateChoiceBox(" id", " buy all", " 0");
                Platform.runLater(() -> messageBox.getChildren().add(new Text("   info: Pls, enter id or buy all....")));
                int[] id = new int[1];
                tavernBuyEquipmentButton.setOnAction(event1 -> {
                    if (isDigit(tavernFieldIdForBuyHealingItems.getText())) {
                        id[0] = Integer.valueOf(tavernFieldIdForBuyEquipmentItems.getText());
                        if (trader.getPriceListEquipmentObjects().containsKey(id[0])) {
                            if (character.getGold() >= trader.getPriceListEquipmentObjects().get(id[0]).getPrice()) {
                                character.setGold(character.getGold() - trader.getPriceListEquipmentObjects().get(id[0]).getPrice());
                                ((Equipment) character).equip(trader.getEquipmentItem(id[0]));
                                updateScreen();
                            } else {
                                alertMessage("   info: Not enough of money!");
                            }
                        } else {
                            alertMessage("   info: Pls, enter correct id....");
                        }
                    } else {
                        alertMessage("   info: Pls, enter correct id....");
                    }
                });
                tavernBuyAllEquipmentButton.setOnAction(event1 -> {
                    for (Map.Entry<Integer, Item> entry :
                            trader.getPriceListEquipmentObjects().entrySet()) {
                        if (character.getGold() > entry.getValue().getPrice()) {
                            character.setGold(character.getGold() - entry.getValue().getPrice());
                            ((Equipment) character).equip(entry.getValue());
                            updateScreen();
                        } else {
                            alertMessage("   info: Not enough of money....");
                        }
                    }
                });
                tavernExitFromEquipment.setOnAction(event1 -> updateGameMenu(tavernMenu));
            });

            tavernHealingItemsButton.setOnAction(event -> {
                updateGameMenu(tavernHealingItemsMenu);
                for (Map.Entry<Integer, HealingItems> entry :
                        trader.getPriceListHealingObjects().entrySet()) {
                    Platform.runLater(() -> messageBox.getChildren().add(new Text("   info: Price: " +
                            entry.getValue().getPrice() + "G - " + "id: " + entry.getKey() + "; " + entry.getValue())));
                }

                updateChoiceBox(" id", " count");
                Platform.runLater(() -> messageBox.getChildren().add(new Text("   info: Pls, enter id or enter 0 for exit....")));
                tavernBuyHealingItemsButton.setOnAction(event1 -> {
                    if (isDigit(tavernFieldIdForBuyHealingItems.getText())) {
                        Integer id = Integer.valueOf(tavernFieldIdForBuyHealingItems.getText());
                        if (trader.getPriceListHealingObjects().containsKey(id)) {
                            if (isDigit(tavernFieldCountForBuyHealingItems.getText())) {
                                int count = Integer.valueOf(tavernFieldCountForBuyHealingItems.getText());
                                if (character.getGold() >= trader.getPriceListHealingObjects().get(id).getPrice() * count) {
                                    character.setGold(character.getGold() - (trader.getPriceListHealingObjects().get(id).getPrice() * count));
                                    ((UsingItems) character).addAll(trader.getHealItems(count, (id)));
                                    updateScreen();
                                } else {
                                    alertMessage("   info: Not enough of money! Pls, enter another count....");
                                }
                            } else {
                                alertMessage("   info: Pls, make the correct choice....");
                            }

                        }
                    } else {
                        alertMessage("   info: Pls, enter correct id....");
                    }

                });
                tavernExitFromHealingItems.setOnAction(event1 -> updateGameMenu(tavernMenu));
            });

            tavernQuestsButton.setOnAction(event -> {
                updateGameMenu(tavernQuestMenu);
                ExtendedText quest = new ExtendedText("   info: " + trader.getQuest(1) + "\n   info: " + trader.getQuest(2) + "\n   info: or exit");
                quest.setFill(Color.BLUEVIOLET);
                updateChoiceBox(" Traders", " Legendary", " Exit");
                Platform.runLater(() -> messageBox.getChildren().add(quest));
                try {
                    quest.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }

                tavernTradersQuest.setOnAction(event1 -> {
                    acceptQuest(trader.getQuest(1));
                    updateGameMenu(tavernMenu);
                    updateScreen();
                });
                tavernLegendaryQuest.setOnAction(event1 -> {
                    acceptQuest(trader.getQuest(0));
                    updateGameMenu(tavernMenu);
                    updateScreen();
                });
                tavernExitFromQuestTalking.setOnAction(event1 -> updateGameMenu(tavernMenu));

            });
            tavernExitFromTavern.setOnAction(event -> nextChoice());
        }

        private void setProgress() {
            task = quest.getLast();
            if (!(task > 0)) {
                character.experienceDrop(quest.getReward());
                if (quest instanceof LegendaryQuest)
                    ((Equipment) character).equip(quest.getItemReward());
                try {
                    quest.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                Platform.runLater(() -> viewQuest.setText("No Quests"));
                quest = null;
                character.setQuest(quest);
            }
        }

        /**
         * Метод проверяющий наличие неиспользованных очков навыков и реализующий их распределение.
         */
        private void checkNewMagicPoint() {

            updateGameMenu(upgradeSkillFirst, upgradeSkillSecond, upgradeSkillThird);

            Platform.runLater(() -> messageBox.getChildren().add(new Text("   info: You have " + character.getMagicPoint())));
            Platform.runLater(() -> messageBox.getChildren().add(new Text("   info: You can upgrade your skills " +
                    (Style.getMagicStyle(character)) + " by index...")));


            new Thread(() -> {
                while (character.getMagicPoint() != 0){
                    System.out.print("");
                }
                nextChoice();
            }).start();
        }

        /**
         * Использование магии
         *
         * @param monster Monster implementation of {@link Monster}
         */
        private void useMagic(Monster monster) {
            ArrayList<Magic> magics = Style.getMagicStyle(character);
            ExtendedText viewSelectMagic = new ExtendedText("   info: Select magic: " + magics);
            updateChoiceBox("1", "2", "3");
            viewSelectMagic.setFill(Color.BLUE);
            Platform.runLater(() -> messageBox.getChildren().add(viewSelectMagic));
            try {
                viewSelectMagic.finalize();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            while (true) {
                while (!canTakeMessage) {
                    System.out.print("");
                }
                String magicChoice = null;
                if (magicChoice.equals("1") || magicChoice.equals("2") || magicChoice.equals("3")) {
                    int mc = Integer.valueOf(magicChoice);
                    mc = --mc;
                    if ((mc < magics.size()) && (mc >= 0)) {
                        Magic magic = magics.get(mc);
                        if (magic.getMagicClass().equals(MagicClasses.COMBAT)) {
                            monster.setDebuff(magic);
                            monster.setHitPoint(monster.getHitPoint() - monster.applyDamage(character.useMagic(magic)));
                            updateScreen();
                            break;
                        } else if (magic.getMagicClass().equals(MagicClasses.HEALING)) {
                            character.setHitPoint(character.getHitPoint() + character.useMagic(magic));
                            updateScreen();
                            break;
                        } else {
                            character.useMagic(magic);
                            updateScreen();
                            break;
                        }
                    }
                } else {
                    ExtendedText notCorrectChoice = new ExtendedText("   info: Pls, make the correct choice....");
                    notCorrectChoice.setFill(Color.RED);
                    Platform.runLater(() -> messageBox.getChildren().add(notCorrectChoice));
                    try {
                        notCorrectChoice.finalize();
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }
            }
        }

        /**
         * Данный метод описывает автоматизированное поведение героя
         * В данном методе герой в цикле while() получает 0.0000001 опыта и случайно выпадающие предметы
         * Остановка цикла происходит при вводе с клавиатуры 0
         *
         * @return String result of walking
         */
        private String walking() {
            updateGameMenu(breakButton);
            new Thread(() -> {
                while (character.getCountOfHealingItems() < 10*character.getLevel() && !breakButton.isPressed()) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    character.experienceDrop(1);
                    HealingItems item = itemsList.get(random.nextInt(sizeOfItems));
                    ExtendedText viewFoundedInfo = new ExtendedText("   info: I found " + item);
                    viewFoundedInfo.setFill(Color.GREEN);
                    Platform.runLater(() -> messageBox.getChildren().add(viewFoundedInfo));
                    try {
                        viewFoundedInfo.finalize();
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                    ((UsingItems) character).add(item);
                    updateScreen();
                }
                nextChoice();

            }).start();

            return "The walk is over";
        }

        /**
         * Метод, реализующий удар монстра и героя. Возвращает true после удара
         *
         * @param monster Monster implementation of {@link Monster}
         */
        private boolean punch(Monster monster) {

            if (character instanceof Wizard) {
                if (character.getManaPoint() > 0)
                    character.setManaPoint(character.getManaPoint() - 50);
                else {
                    if (character.healManaPoint()) {
                        character.setManaPoint(character.getManaPoint() - 50);
                        return true;
                    } else return false;
                }
            }

            monster.setHitPoint((monster.getHitPoint() - monster.applyDamage(character.getDamage())));
            character.setHitPoint((character.getHitPoint() - character.applyDamage(monster.getDamageForBattle())));
            ExtendedText extendedText = new ExtendedText("   info: " + monster.toString());
            extendedText.setFill(Color.ORANGERED);
            Platform.runLater(() -> messageBox.getChildren().add(extendedText));
            updateScreen();
            if (slowGameStyle.isSelected()) try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                extendedText.finalize();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return true;
        }

        /**
         * Метод предназначенный для автоматического восполнения здоровья
         * Возвращает true в случае успешного восполнения здоровья и false в случае если этого не произошло
         *
         * @return boolean result of healHitPoint
         */
        private boolean autoHeal() {

            if (character.checkHitPointBottle()) {
                return character.healHitPoint();
            } else if (!character.checkHitPointBottle() && (character.checkManaPointBottle())) {
                if (character.getManaPoint() >= SmallHealing.magicFactory.getMagicFactory(character.getLevel()).getManaCost()) {
                    Magic magic = SmallHealing.magicFactory.getMagicFactory(character.getLevel());
                    character.useMagic(magic);
                    return true;
                } else {
                    if (character.healManaPoint()) {
                        Magic magic = SmallHealing.magicFactory.getMagicFactory(character.getLevel());
                        character.useMagic(magic);
                        return true;
                    } else return false;
                }
            } else return false;
        }


        /**
         * Пользователю предлагается использовать один из имеющихся у него предметов,
         * предвартельно ознакомив его с содержимым инвентаря. Доступ к предмету осществляется по индексу.
         * <p>
         * После ввода индекса осуществляется проверка на наличие этого предмета в инвентаре, после чего вызывается
         * метод use() из класса персонажа.
         *
         * @param character Character implementation of {@link Character}
         * @return boolean result of using item
         */
        private void useItem(Character character) {
            Platform.runLater(() -> {

                Text viewUsingItems = new Text("   info: Use your itemsclasses? " +
                        "BigHitPointBottle: " + character.getCountOfBigHitPointBottle() +
                        "; MiddleHitPointBottle: " + character.getCountOfMiddleHitPointBottle() +
                        "; SmallHitPointBottle: " + character.getCountOfSmallHitPointBottle() +
                        ";\n        BigFlower: " + character.getCountOfBigFlower() +
                        "; MiddleFlower: " + character.getCountOfMiddleFlower() +
                        "; SmallFlower: " + character.getCountOfSmallFlower() + "\n   info: Pls, select by index....");
                viewUsingItems.setFill(Color.GREEN);
                messageBox.getChildren().add(viewUsingItems);
            });

            updateGameMenu(healingItemMenu);

        }

        /**
         * Метод, вызывающийся по окончанию боя с монстром.
         * В качестве входных параметров метод принимает объекты героя и монстра
         * для установления факта смерти одного из них и логический тип mode
         * для определения режима последующего дропа снаряжения.
         * <p>
         * В случае смерти героя вызывается метод exit() и игра завершается.
         * В случае смерти монстра вызывается метод drop(), в котором герой может поднять
         * снаряжение оставшееся после убитого монстра.
         *
         * @param character Character implementation of {@link Character}
         * @param monster   Monster implementation of {@link Monster}
         * @param mode      boolean mode for drop itemsclasses
         */
        private void endEvent(Character character, Monster monster, boolean mode) {
            if (character.getHitPoint() <= 0) {
                Platform.runLater(() -> messageBox.getChildren().add(new Text("   info: YOU ARE DEAD")));
                exit();
            } else if (monster.getHitPoint() <= 0) {
                drop(character, monster, mode);
            }
        }

        /**
         * После смерти монстра выпадает случайный премет из списка Item.
         * У игрока есть возможность поднять этот предмет и переместить в свой инвентарь
         * <p>
         * Входные параметры:
         *
         * @param character Character implementation of {@link Character}
         * @param monster   Monster implementation of {@link Monster}
         * @return boolean result
         */
        private boolean drop(Character character, Monster monster, boolean autoDrop) {

            if (!Objects.equals(quest, null)) {
                setProgress();
            }

            if (autoDrop) {
                if (character.experienceDrop(monster.getExperience())) exit();
                ((UsingItems) character).add(monster.getInventory().pollLast());
                character.setGold(character.getGold() + monster.getDroppedGold());
                Map<EquipmentItems, Item> droppedEquipment = monster.getDroppedItems();
                for (Map.Entry<EquipmentItems, Item> entry : droppedEquipment.entrySet()) {
                    ((Equipment) character).equip(entry.getValue());
                }
                updateScreen();
                try {
                    finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                return true;
            } else {
                if (!Objects.equals(monster.getDroppedItems(), null)) {
                    Map<EquipmentItems, Item> droppedEquipment = monster.getDroppedItems();
                    character.setGold(character.getGold() + monster.getDroppedGold());
                    Platform.runLater(() -> {
                        Text droppedGold = new Text("   info: You have found " + monster.getDroppedGold() + "G");
                        droppedGold.setFill(Color.BROWN);
                        messageBox.getChildren().add(droppedGold);
                        Text viewDroppedEquipment = new Text("   info: You have found: " + droppedEquipment.toString());
                        viewDroppedEquipment.setFill(Color.BLUE);
                        messageBox.getChildren().add(viewDroppedEquipment);
                        messageBox.getChildren().add(new Text("   info: Equip all, or manual?"));
                    });
                    String equipAll = null;
                    while (true) {
                        updateChoiceBox(" equip all", " manual");
                        if (Objects.equals(equipAll, "equip all")) {
                            for (Map.Entry<EquipmentItems, Item> entry : droppedEquipment.entrySet()) {
                                ((Equipment) character).equip(entry.getValue());
                            }
                            updateScreen();
                            break;
                        } else if (Objects.equals(equipAll, "manual")) {
                            break;
                        } else Platform.runLater(() -> {
                            Text notCorrectChoice = new Text("   info: Pls, make the correct choice....");
                            notCorrectChoice.setFill(Color.RED);
                            messageBox.getChildren().add(notCorrectChoice);
                        });
                    }

                    if (Objects.equals(equipAll, "manual"))
                        while (true) {
                            Platform.runLater(() -> messageBox.getChildren().add(new Text("   info: Pls, choose equipmentclasses....")));
                            Platform.runLater(() -> messageBox.getChildren().add(new Text("   info:" + droppedEquipment.toString())));
                            String key = null;
                            List<String> list = Arrays.asList("HEAD", "HANDS", "LEGS", "ARMOR");
                            updateChoiceBox(" HEAD", " HANDS", " LEGS", " ARMOR");
                            while (true) {
                                while (!canTakeMessage) {
                                    System.out.print("");
                                }
                                if (list.contains(key)) break;
                                else
                                    Platform.runLater(() -> messageBox.getChildren().add(new Text("   info: Pls, enter another key....")));
                            }
                            ((Equipment) character).equip(droppedEquipment.get(EquipmentItems.valueOf(key)));
                            updateScreen();
                            droppedEquipment.remove((EquipmentItems.valueOf(key)));
                            Platform.runLater(() -> messageBox.getChildren().add(new Text("   info: Equip more?")));
                            while (!canTakeMessage) {
                                System.out.print("");
                            }
//                            String exit = getChoice();
//                            if (Objects.equals(exit, "No") || droppedEquipment.isEmpty()) break;
                        }
                }
                if (character.experienceDrop(monster.getExperience())) exit();
                Platform.runLater(() -> {
                    Text viewFoundedHealingItems = new Text("   info: You can add to your inventory " + monster.getInventory());
                    viewFoundedHealingItems.setFill(Color.GREEN);
                    messageBox.getChildren().add(viewFoundedHealingItems);
                });
                while (true) {
                    updateChoiceBox(" add", " skip");
                    while (!canTakeMessage) {
                        System.out.print("");
                    }
//                    String choice = getChoice();
                    if (Objects.equals(choice, "add")) {
                        ((UsingItems) character).add(monster.getInventory().pollLast());
                        updateScreen();
                        break;
                    } else if (Objects.equals(choice, "skip")) break;
                    else Platform.runLater(() -> {
                            Text notCorrectChoice = new Text("   info: Pls, make the correct choice....");
                            notCorrectChoice.setFill(Color.RED);
                            messageBox.getChildren().add(notCorrectChoice);
                        });
                }
                return true;
            }
        }

        /**
         * Метод, отвечающий за генерацию монстра
         *
         * @param character Character implementation of {@link Character}
         * @return New implementation of {@link Monster} with incremented character level
         */
        private Monster spawn(Character character) {
            int chance = random.nextInt(100);
            Monster newMonster;
            if (character.getLevel() == 1) {
                newMonster = EasyBot.monsterFactory.createNewMonster(character);
            } else if (character.getLevel() % 3 == 0) {
                newMonster = Boss.monsterFactory.createNewMonster(character);
            } else if ((character.getLevel() > 3) || (character.getLevel() < 5)) {
                if ((chance > 0) && (chance < 25)) {
                    newMonster = MediumBot.monsterFactory.createNewMonster(character);
                } else {
                    newMonster = EasyBot.monsterFactory.createNewMonster(character);
                }
            } else if ((character.getLevel() > 4) && (character.getLevel() < 8)) {
                if ((chance > 0) && (chance < 25)) {
                    newMonster = HardBot.monsterFactory.createNewMonster(character);
                } else {
                    newMonster = MediumBot.monsterFactory.createNewMonster(character);
                }
            } else if ((character.getLevel() > 8) && (character.getLevel() < 12)) {
                if ((chance > 0) && (chance < 25)) {
                    newMonster = VeryHardBot.monsterFactory.createNewMonster(character);
                } else {
                    newMonster = HardBot.monsterFactory.createNewMonster(character);
                }
            } else if ((character.getLevel() > 12) && (character.getLevel() < 15)) {
                if ((chance > 0) && (chance < 25)) {
                    newMonster = UltraHardBot.monsterFactory.createNewMonster(character);
                } else {
                    newMonster = VeryHardBot.monsterFactory.createNewMonster(character);
                }
            } else if ((character.getLevel() > 15) && (character.getLevel() < 18)) {
                if ((chance > 0) && (chance < 25)) {
                    newMonster = BrutalHardBot.monsterFactory.createNewMonster(character);
                } else {
                    newMonster = UltraHardBot.monsterFactory.createNewMonster(character);
                }
            } else newMonster = BrutalHardBot.monsterFactory.createNewMonster(character);
            aliveMonster = newMonster;
            return newMonster;
        }

        /**
         * End of game
         */
        private void exit() {
            Text game_over = new Text("   info: GAME OVER");
            game_over.setFill(Color.RED);
            Platform.runLater(() -> {
                messageBox.getChildren().add(game_over);
                gameMenuBox.getChildren().clear();
            });
            System.gc();
        }

        private HealingItems getBigHitPointBottle() {
            HealingItems bottle = null;
            if (character.getCountOfBigHitPointBottle() != 0) {
                bottle = BigHPBottle.healingHitPointItemsFactory.getNewHealingHitPointItem();
            }
            return bottle;
        }

        private HealingItems getMiddleHitPointBottle() {
            HealingItems bottle = null;
            if (character.getCountOfMiddleHitPointBottle() != 0) {
                bottle = MiddleHPBottle.healingHitPointItemsFactory.getNewHealingHitPointItem();
            }
            return bottle;
        }

        private HealingItems getSmallHitPointBottle() {
            HealingItems bottle = null;
            if (character.getCountOfSmallHitPointBottle() != 0) {
                bottle = SmallHPBottle.healingHitPointItemsFactory.getNewHealingHitPointItem();
            }
            return bottle;
        }

        private HealingItems getBigFlower() {
            HealingItems bottle = null;
            if (character.getCountOfBigFlower() != 0) {
                bottle = BigFlower.healingHitPointItemsFactory.getNewHealingManaPointItem();
            }
            return bottle;
        }

        private HealingItems getMiddleFlower() {
            HealingItems bottle = null;
            if (character.getCountOfMiddleFlower() != 0) {
                bottle = MiddleFlower.healingManaPointItemsFactory.getNewHealingManaPointItem();
            }
            return bottle;
        }

        private HealingItems getSmallFlower() {
            HealingItems bottle = null;
            if (character.getCountOfSmallFlower() != 0) {
                bottle = SmallFlower.healingManaPointItemsFactory.getNewHealingManaPointItem();
            }
            return bottle;
        }

        private synchronized void updateScreen() {

            ExtendedText viewHealingBigHitPointBottles = new ExtendedText("BigHPBottles: " + character.getCountOfBigHitPointBottle());
            viewHealingBigHitPointBottles.setFill(Color.INDIGO);
            ExtendedText viewHealingMiddleHitPointBottles = new ExtendedText("MiddleHPBottles: " + character.getCountOfMiddleHitPointBottle());
            viewHealingMiddleHitPointBottles.setFill(Color.INDIGO);
            ExtendedText viewHealingSmallHitPointBottles = new ExtendedText("SmallHPBottles: " + character.getCountOfSmallHitPointBottle());
            viewHealingSmallHitPointBottles.setFill(Color.INDIGO);
            ExtendedText viewHealingBigFlowers = new ExtendedText("BigFlowers: " + character.getCountOfBigFlower());
            viewHealingBigFlowers.setFill(Color.BLUE);
            ExtendedText viewHealingMiddleFlowers = new ExtendedText("MiddleFlowers: " + character.getCountOfMiddleFlower());
            viewHealingMiddleFlowers.setFill(Color.BLUE);
            ExtendedText viewHealingSmallFlowers = new ExtendedText("SmallFlowers: " + character.getCountOfSmallFlower());
            viewHealingSmallFlowers.setFill(Color.BLUE);

            Platform.runLater(() -> {
                viewName.setText("NAME: " + character.getName());
                viewClass.setText("CLASS: " + character.getClass().getSimpleName());
                viewLevel.setText("LVL: " + character.getLevel());
                viewExp.setText("Experience to next level:\n" + String.valueOf((int) character.expToNextLevel()));
                viewHitPoint.setText("HP: " + character.getHitPoint());
                viewManaPoint.setText("MP: " + character.getManaPoint());
                viewAttack.setText("ATK: " + character.getDamage());
                viewGold.setText("GOLD: " + character.getGold());
                if (!Objects.equals(quest, null))
                    Platform.runLater(() -> viewQuest.setText("QUEST: kill " + task + " enemy"));

                itemBox.getChildren().clear();
                itemBox.getChildren().add(viewHealingBigHitPointBottles);
                itemBox.getChildren().add(viewHealingMiddleHitPointBottles);
                itemBox.getChildren().add(viewHealingSmallHitPointBottles);
                itemBox.getChildren().add(viewHealingBigFlowers);
                itemBox.getChildren().add(viewHealingMiddleFlowers);
                itemBox.getChildren().add(viewHealingSmallFlowers);
            });

            try {
                viewHealingBigHitPointBottles.finalize();
                viewHealingBigFlowers.finalize();
                viewHealingMiddleHitPointBottles.finalize();
                viewHealingMiddleFlowers.finalize();
                viewHealingSmallHitPointBottles.finalize();
                viewHealingSmallFlowers.finalize();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }

            if (!((Equipment) character).showEquipment().isEmpty()) {
                Platform.runLater(() -> equipmentBox.getChildren().clear());
                for (Map.Entry<EquipmentItems, Item> entry :
                        ((Equipment) character).showEquipment().entrySet()) {
                    Text equipment = new Text(entry.getKey() + ": " + entry.getValue());
                    Platform.runLater(() -> equipmentBox.getChildren().add(equipment));

                }
            }
        }

        private synchronized void updateChoiceBox(String... choice) {
            Text title = new Text("Current:");
            title.setFill(Color.BLACK);
            title.setFont(Font.font("Ubuntu", 18));
            Platform.runLater(() -> {
                currentChoiceBox.getChildren().clear();
                currentChoiceBox.getChildren().add(title);
            });

            LinkedList<Text> cases = new LinkedList<>();
            cases.add(caseFirst);
            cases.add(caseSecond);
            cases.add(caseThird);
            cases.add(caseFourth);
            cases.add(caseFifth);
            cases.add(caseSixth);

            for (String ch : choice) {
                Platform.runLater(() -> {
                    cases.pollFirst().setText(ch);
                });
            }

            if (!cases.isEmpty()) {
                for (Text t :
                        cases) {
                    t.setText("");
                }
            }

            Task task = new Task() {
                @Override
                protected Object call() throws Exception {
                    for (String s :
                            choice) {
                        Text current = new Text(s);
                        current.setFont(Font.font("Ubuntu", 14));
                        Platform.runLater(() -> currentChoiceBox.getChildren().add(current));
                    }
                    return null;
                }
            };
            Thread t = new Thread(task);
            t.start();
        }

        private boolean isDigit(String s) throws NumberFormatException {
            try {
                Integer.parseInt(s);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

    @FXML
    public void initialize() {
        Text text = new Text("Hello from new world implementation!" + "\nPls, choice your hero: archer, berserk, wizard....");
        messageBox.getChildren().add(text);
        messageBoxScrollPane.vvalueProperty().bind(messageBox.heightProperty());
        character = Wizard.characterFactory.createNewCharacter();
        Button archerButton = new Button("Archer");
        archerButton.setOnAction(event -> {
            character = Archer.characterFactory.createNewCharacter();
            launch();
        });
        Button berserkButton = new Button("Berserk");
        berserkButton.setOnAction(event -> {
            character = Berserk.characterFactory.createNewCharacter();
            launch();
        });
        Button wizardButton = new Button("Wizard");
        wizardButton.setOnAction(event -> {
            character = Wizard.characterFactory.createNewCharacter();
            launch();
        });
        Platform.runLater(() -> {
            gameMenuBox.getChildren().add(archerButton);
            gameMenuBox.getChildren().add(berserkButton);
            gameMenuBox.getChildren().add(wizardButton);
        });

    }

    private void acceptQuest(Quest quest) {
        this.quest = quest;
        this.task = quest.getTask();
        character.setQuest(quest);
    }

    private void launch() {
        innerPlayerControllerClass = new InnerPlayerControllerClass();
        Thread thread = new Thread(innerPlayerControllerClass);
        thread.start();
    }

    public void serialize() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("xml-file", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(new Stage());
        String filePath;
        if (!Objects.equals(file, null)) {
            filePath = file.getAbsolutePath();
            save(filePath);
        }
    }

    public void deserialize() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("xml-file", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        String filePath = fileChooser.showOpenDialog(new Stage()).getAbsolutePath();
        if (!Objects.equals(filePath, null)) {
            load(filePath);
        }
    }

    private void save(String filePath) {

        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(myEncryptionPassword);

        XStream xStream = new XStream();
        String xml = xStream.toXML(character);
        String myEncryptedText = textEncryptor.encrypt(xml);
        XMLEncoder encoder = null;
        try {
            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filePath)));
        } catch (FileNotFoundException fileNotFound) {
            fileNotFound.printStackTrace();
        }
        if (encoder != null) {
            encoder.writeObject(myEncryptedText);
            encoder.close();
        }
    }

    private void load(String path) {

        character = null;

        BasicTextEncryptor textDecryptor = new BasicTextEncryptor();
        textDecryptor.setPassword(myEncryptionPassword);

        XStream xStream = new XStream();
        XMLDecoder decoder = null;
        try {
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(path)));
            String xml = decoder.readObject().toString();
            String plainText = textDecryptor.decrypt(xml);
            character = (Character) xStream.fromXML(plainText);
        } catch (FileNotFoundException fileNotFound) {
            fileNotFound.printStackTrace();
        }
        if (!Objects.equals(character.getQuest(), null))
            acceptQuest(character.getQuest());
        decoder.close();

        if (Objects.equals(innerPlayerControllerClass, null)) {
            innerPlayerControllerClass = new InnerPlayerControllerClass();
            Thread thread = new Thread(innerPlayerControllerClass);
            thread.start();
        }
    }

    public void about() {
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);

        Text text = (new Text(40, 40, "Текстовая RPG \"Time of the Legends\"\n" +
                "Developer: Artem Nikulin\n" +
                "Testers: Alexey Kostenikov & Ivan Kostenikov\n" +
                "Contact email: it.chubaka@gmail.com\n"));
        text.setStyle("-fx-text-fill: #3c7fb1");
        text.setTextAlignment(TextAlignment.CENTER);
        Group group = new Group(text);
        group.setStyle("-fx-background-color: #1d1d1d");
        Scene scene = new Scene(group);
        dialog.setScene(scene);
        dialog.setResizable(false);
        dialog.show();
    }

    public void exit() {
        Text text = new Text("\nGAME OVER\n");
        messageBox.getChildren().add(text);

        if (character.getHitPoint() > 0) serialize();

        System.exit(0);
    }
}