package com.gmail.nossr50.runnables;

import java.io.BufferedReader;
import java.io.FileReader;

import org.bukkit.Bukkit;

import com.gmail.nossr50.m;
import com.gmail.nossr50.mcMMO;
import com.gmail.nossr50.config.LoadProperties;

public class SQLConversionTask implements Runnable {
    public SQLConversionTask() {}

    @Override
    public void run() {
        String location = "plugins/mcMMO/FlatFileStuff/mcmmo.users";

        try {
            FileReader file = new FileReader(location);
            BufferedReader in = new BufferedReader(file);
            String line = "";
            String playerName = null;
            String party = null;
            String mining = null;
            String woodcutting = null;
            String repair = null;
            String unarmed = null;
            String herbalism = null;
            String excavation = null;
            String archery = null;
            String swords = null;
            String axes = null;
            String acrobatics = null;
            String taming = null;
            String fishing = null;
            String miningXP = null;
            String woodCuttingXP = null;
            String repairXP = null;
            String unarmedXP = null;
            String herbalismXP = null;
            String excavationXP = null;
            String archeryXP = null;
            String swordsXP = null;
            String axesXP = null;
            String acrobaticsXP = null;
            String tamingXP = null;
            String fishingXP = null;
            int id = 0;
            int theCount = 0;

            while ((line = in.readLine()) != null) {

                //Find if the line contains the player we want.
                String[] character = line.split(":");
                playerName = character[0];

                //Check for things we don't want put in the DB
                if (playerName == null || playerName.equals("null") || playerName.equals("#Storage place for user information")) {
                    continue;
                }

                if (character.length > 1) {
                    mining = character[1];
                }

                if (character.length > 3) {
                    party = character[3];
                }

                if (character.length > 4) {
                    miningXP = character[4];
                }

                if (character.length > 5) {
                    woodcutting = character[5];
                }

                if (character.length > 6) {
                    woodCuttingXP = character[6];
                }

                if (character.length > 7) {
                    repair = character[7];
                }

                if (character.length > 8) {
                    unarmed = character[8];
                }

                if (character.length > 9) {
                    herbalism = character[9];
                }

                if (character.length > 10) {
                    excavation = character[10];
                }

                if (character.length > 11) {
                    archery = character[11];
                }

                if (character.length > 12) {
                    swords = character[12];
                }

                if (character.length > 13) {
                    axes = character[13];
                }

                if (character.length > 14) {
                    acrobatics = character[14];
                }

                if (character.length > 15) {
                    repairXP = character[15];
                }

                if (character.length > 16) {
                    unarmedXP = character[16];
                }

                if (character.length > 17) {
                    herbalismXP = character[17];
                }

                if (character.length > 18) {
                    excavationXP = character[18];
                }

                if (character.length > 19) {
                    archeryXP = character[19];
                }

                if (character.length > 20) {
                    swordsXP = character[20];
                }

                if (character.length > 21) {
                    axesXP = character[21];
                }

                if (character.length > 22) {
                    acrobaticsXP = character[22];
                }

                if (character.length > 24) {
                    taming = character[24];
                }

                if (character.length > 25) {
                    tamingXP = character[25];
                }

                if (character.length > 34) {
                    fishing = character[34];
                }

                if (character.length > 35) {
                    fishingXP = character[35];
                }

                //Check to see if the user is in the DB
                id = mcMMO.database.getInt("SELECT id FROM "
                        + LoadProperties.MySQLtablePrefix
                        + "users WHERE user = '" + playerName + "'");

                if (id > 0) {
                    theCount++;

                    //Update the skill values
                    mcMMO.database.write("UPDATE "
                            + LoadProperties.MySQLtablePrefix
                            + "users SET lastlogin = " + 0
                            + " WHERE id = " + id);
                    mcMMO.database.write("UPDATE "
                            + LoadProperties.MySQLtablePrefix
                            + "skills SET " + "  taming = taming+"
                            + m.getInt(taming) + ", mining = mining+"
                            + m.getInt(mining) + ", repair = repair+"
                            + m.getInt(repair)
                            + ", woodcutting = woodcutting+"
                            + m.getInt(woodcutting)
                            + ", unarmed = unarmed+" + m.getInt(unarmed)
                            + ", herbalism = herbalism+"
                            + m.getInt(herbalism)
                            + ", excavation = excavation+"
                            + m.getInt(excavation)
                            + ", archery = archery+" + m.getInt(archery)
                            + ", swords = swords+" + m.getInt(swords)
                            + ", axes = axes+" + m.getInt(axes)
                            + ", acrobatics = acrobatics+"
                            + m.getInt(acrobatics)
                            + ", fishing = fishing+" + m.getInt(fishing)
                            + " WHERE user_id = " + id);
                    mcMMO.database.write("UPDATE "
                            + LoadProperties.MySQLtablePrefix
                            + "experience SET " + "  taming = "
                            + m.getInt(tamingXP) + ", mining = "
                            + m.getInt(miningXP) + ", repair = "
                            + m.getInt(repairXP) + ", woodcutting = "
                            + m.getInt(woodCuttingXP) + ", unarmed = "
                            + m.getInt(unarmedXP) + ", herbalism = "
                            + m.getInt(herbalismXP) + ", excavation = "
                            + m.getInt(excavationXP) + ", archery = "
                            + m.getInt(archeryXP) + ", swords = "
                            + m.getInt(swordsXP) + ", axes = "
                            + m.getInt(axesXP) + ", acrobatics = "
                            + m.getInt(acrobaticsXP) + ", fishing = "
                            + m.getInt(fishingXP) + " WHERE user_id = "
                            + id);
                }
                else {
                    theCount++;

                    //Create the user in the DB
                    mcMMO.database.write("INSERT INTO "
                            + LoadProperties.MySQLtablePrefix
                            + "users (user, lastlogin) VALUES ('"
                            + playerName + "',"
                            + System.currentTimeMillis() / 1000 + ")");
                    id = mcMMO.database.getInt("SELECT id FROM "
                                    + LoadProperties.MySQLtablePrefix
                                    + "users WHERE user = '"
                                    + playerName + "'");
                    mcMMO.database.write("INSERT INTO "
                            + LoadProperties.MySQLtablePrefix
                            + "skills (user_id) VALUES (" + id + ")");
                    mcMMO.database.write("INSERT INTO "
                            + LoadProperties.MySQLtablePrefix
                            + "experience (user_id) VALUES (" + id
                            + ")");
                    //Update the skill values
                    mcMMO.database.write("UPDATE "
                            + LoadProperties.MySQLtablePrefix
                            + "users SET lastlogin = " + 0
                            + " WHERE id = " + id);
                    mcMMO.database.write("UPDATE "
                            + LoadProperties.MySQLtablePrefix
                            + "users SET party = '" + party
                            + "' WHERE id = " + id);
                    mcMMO.database.write("UPDATE "
                            + LoadProperties.MySQLtablePrefix
                            + "skills SET " + "  taming = "
                            + m.getInt(taming) + ", mining = "
                            + m.getInt(mining) + ", repair = "
                            + m.getInt(repair) + ", woodcutting = "
                            + m.getInt(woodcutting) + ", unarmed = "
                            + m.getInt(unarmed) + ", herbalism = "
                            + m.getInt(herbalism) + ", excavation = "
                            + m.getInt(excavation) + ", archery = "
                            + m.getInt(archery) + ", swords = "
                            + m.getInt(swords) + ", axes = "
                            + m.getInt(axes) + ", acrobatics = "
                            + m.getInt(acrobatics) + ", fishing = "
                            + m.getInt(fishing) + " WHERE user_id = "
                            + id);
                    mcMMO.database.write("UPDATE "
                            + LoadProperties.MySQLtablePrefix
                            + "experience SET " + "  taming = "
                            + m.getInt(tamingXP) + ", mining = "
                            + m.getInt(miningXP) + ", repair = "
                            + m.getInt(repairXP) + ", woodcutting = "
                            + m.getInt(woodCuttingXP) + ", unarmed = "
                            + m.getInt(unarmedXP) + ", herbalism = "
                            + m.getInt(herbalismXP) + ", excavation = "
                            + m.getInt(excavationXP) + ", archery = "
                            + m.getInt(archeryXP) + ", swords = "
                            + m.getInt(swordsXP) + ", axes = "
                            + m.getInt(axesXP) + ", acrobatics = "
                            + m.getInt(acrobaticsXP) + ", fishing = "
                            + m.getInt(fishingXP) + " WHERE user_id = "
                            + id);
                }
            }

            System.out.println("[mcMMO] MySQL Updated from users file, " + theCount + " items added/updated to MySQL DB");
            in.close();
        }
        catch (Exception e) {
            Bukkit.getLogger().severe("Exception while reading " + location + " (Are you sure you formatted it correctly?)" + e.toString());
        }
    }
}
