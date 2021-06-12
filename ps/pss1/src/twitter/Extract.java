/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

    private static final String validUserNameChar =
        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-";
    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */
    public static Timespan getTimespan(List<Tweet> tweets) {
        if (tweets == null){
            throw new RuntimeException("list of tweets can not be null");
        }
        Instant startTime = tweets.get(0).getTimestamp();
        Instant endTime = tweets.get(0).getTimestamp();
        for (Tweet tweet : tweets){
            Instant curTweetTime = tweet.getTimestamp();
            if (curTweetTime.isBefore(startTime)){
                startTime = curTweetTime;
            }else if (curTweetTime.isAfter(endTime)){
                endTime = curTweetTime;
            }
        }
        return new Timespan(startTime, endTime);
    }

    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like bitdiddle@mit.edu does NOT 
     *         contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
        if (tweets == null){
            throw new RuntimeException("list of tweets can not be null");
        }
        Set<String> mentionedUsers = new HashSet<>();
        for (Tweet tweet : tweets){
            int startIdx = 0;
            String text = tweet.getText().toLowerCase();
            int textLen = text.length();
            while (startIdx < textLen && (startIdx = text.indexOf('@', startIdx)) != -1){
                if (startIdx != 0 &&
                    validUserNameChar.indexOf(text.charAt(startIdx - 1)) != -1){
                    startIdx++;
                    continue;
                }
                int newIdx = startIdx + 1;
                while(newIdx < textLen && validUserNameChar.indexOf(text.charAt(newIdx)) != -1)
                {
                    newIdx++;
                }
                String userName = text.substring(startIdx + 1, newIdx);
                if (userName != null && userName.length() > 0){
                    mentionedUsers.add(userName);
                }
                startIdx = newIdx;
            }
        }
        return mentionedUsers;
    }

}
