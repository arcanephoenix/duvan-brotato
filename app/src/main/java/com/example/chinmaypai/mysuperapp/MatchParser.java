package com.example.chinmaypai.mysuperapp;

import com.example.chinmaypai.mysuperapp.models.Match;
import com.example.chinmaypai.mysuperapp.models.MatchData;

import org.apache.commons.lang3.StringUtils;



/**
 * Created by Chinmay Pai on 07-11-2016.
 */

public class MatchParser {
    public static Match parseMatch(MatchData matchData) {
        //int charindex=0;
        //boolean hasScore=false;
        Match match = new Match();
        String theData = matchData.getDescription()+" ";

        String firsthalf = theData.substring(0, theData.indexOf(" v "));
        String secondhalf = theData.substring(theData.indexOf(" v ") + 3);
        //parseEach(match,firsthalf);
        if (!(firsthalf.matches(".*\\d+.*"))) {
            match.team_1 = firsthalf;
            match.team_2 = secondhalf;
            return match;
        }

        for (int i = 0; i < firsthalf.length(); i++) {
            //char c=firsthalf.charAt(i);
            if (firsthalf.charAt(i) == ' ') {

                if (Character.isDigit(firsthalf.charAt(i + 1))) {
                    //hasScore=true;
                    match.team_1 = firsthalf.substring(0, i);
                    String allscores = firsthalf.substring(i + 1);
                    if (allscores.indexOf('&') != -1) //if there is a & sign i.e. confirmed test match
                    {

                        String score1 = allscores.substring(0, allscores.indexOf('&'));
                        //System.out.println(score1);
                        match.team_1_first.runs = Integer.parseInt(score1.substring(0, score1.indexOf('/')).trim());
                        match.team_1_first.wickets = Integer.parseInt(score1.substring(score1.indexOf('/') + 1, score1.indexOf('/') + 3).trim());
                        //System.out.println (runs1+"/"+wickets1);
                        String score2 = allscores.substring((allscores.indexOf('&') + 6));
                        match.team_1_second.runs = Integer.parseInt(score2.substring(0, score2.indexOf('/')).trim());
                        match.team_1_second.wickets = Integer.parseInt(score2.substring(score2.indexOf('/') + 1, score2.indexOf('/') + 3).trim());
                        //System.out.println (runs2+"/"+wickets2);


                    } else {
                        if (allscores.indexOf('/') != -1) {
                            match.team_1_first.runs = Integer.parseInt(allscores.substring(0, allscores.indexOf('/')).trim());
                            match.team_1_first.wickets = Integer.parseInt(allscores.substring(allscores.indexOf('/') + 1, allscores.indexOf('/') + 3).trim());
                        } else {   //there are no / in the score
                            match.team_1_first.runs = Integer.parseInt(allscores.substring(0, allscores.indexOf(' ')).trim());
                        }

                    }
                    break;
                }
            }
        }

            for (int i = 0; i < secondhalf.length(); i++) {
                if (secondhalf.charAt(i) == ' ') {
                    if ((i+1)>=secondhalf.length()) {
                        match.team_2 = secondhalf.trim();
                        break;
                    }
                    else if (Character.isDigit(secondhalf.charAt(i + 1))) {
                        match.team_2 = secondhalf.substring(0, i);
                        String allscores = secondhalf.substring(i + 1);
                        if (allscores.indexOf('&') != -1) //if there is a & sign i.e. confirmed test match
                        {
                            match.isTest = true;
                            String score1 = allscores.substring(0, allscores.indexOf('&'));
                            //System.out.println(score1);
                            if (score1.indexOf('/') != -1) {
                                match.team_2_first.runs = Integer.parseInt(score1.substring(0, score1.indexOf('/')));
                                match.team_2_first.wickets = Integer.parseInt(score1.substring(score1.indexOf('/') + 1, score1.indexOf('/') + 3).trim());
                            }
                            else {
                                match.team_2_first.runs = Integer.parseInt(allscores.substring(0, allscores.indexOf(' ')).trim());
                            }
                            //System.out.println (runs1+"/"+wickets1);
                            String score2 = allscores.substring((allscores.indexOf('&') + 6));
                            if(score2.indexOf('/') != 1) {
                                match.team_2_second.runs = Integer.parseInt(score2.substring(0, score2.indexOf('/')).trim());
                                match.team_2_second.wickets = Integer.parseInt(score2.substring(score2.indexOf('/') + 1, score2.indexOf('/') + 3).trim());
                            }
                            else {
                            match.team_2_first.runs = Integer.parseInt(allscores.substring(0, allscores.indexOf(' ')).trim());
                        }
                            //System.out.println (runs2+"/"+wickets2);
                        } else {

                            match.isTest = false;
                            if (allscores.indexOf('/') != -1) {
                                match.team_2_first.runs = Integer.parseInt(allscores.substring(0, allscores.indexOf('/')));
                                match.team_2_first.wickets = Integer.parseInt(allscores.substring(allscores.indexOf('/') + 1, allscores.indexOf('/') + 3).trim());
                            }
                            else {
                                match.team_2_first.runs = Integer.parseInt(allscores.substring(0, allscores.indexOf(' ')).trim());
                            }
                        }
                        break;
                    }
                }
            }
            return match;

    }
}