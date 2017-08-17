package game.model.Quests;

import game.model.Characters.Character;

public interface QuestFactory {
    Quest createNewQuest(Character character);
}
