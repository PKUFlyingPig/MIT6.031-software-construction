/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class ExtractTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     *  getTimeSpan test strategy:
     *      partition on tweets.size():
     *          tweets.size() == 1
     *          tweets.size() > 1
     *      partition on tweets's earliest tweet
     *          index = 0
     *          index >= 1
     *  getMentionedUsers test strategy:
     *      if contain tricky situation
     *          contains sfdj@mit.edu.cn
     *          not contain sdfj@mit.edu.cn
     *      partition on number of mentioned users
     *          n == 0
     *          n == 1
     *          n >= 2
     *      test case-insensitive
     *          have same except for case name
     *          don't have ...
     *
     */
    
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");

    private static final Tweet tweet0 = new Tweet(0, "ksdfj", "I love you baby.", d1);
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much? @Sb", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in sfdjk@mit.edu.cn 30 minutes #hype", d2);
    private static final Tweet tweet3 = new Tweet(3, "skdjfal", "I like @haha and @HAHA", d2);

    /** cover tweets.size() == 1. */
    @Test
    public void oneTweetGetTimeSpan(){
        List<Tweet> tweets = new ArrayList<>();
        tweets.add(tweet1);
        Timespan ts = Extract.getTimespan(tweets);
        assertEquals(d1, ts.getStart());
        assertEquals(d1, ts.getEnd());
    }

    /** cover tweets.size() > 1 and earliest tweet index == 0. */
    @Test
    public void moreTweetsGetTimeSpan(){
        List<Tweet> tweets = new ArrayList<>();
        tweets.add(tweet1);
        tweets.add(tweet2);
        Timespan ts = Extract.getTimespan(tweets);
        assertEquals(d1, ts.getStart());
        assertEquals(d2, ts.getEnd());
    }

    /** cover tweets.size() > 1 and earliest tweet index >= 1. */
    @Test
    public void moreTweetsGetTimeSpan1(){
        List<Tweet> tweets = new ArrayList<>();
        tweets.add(tweet2);
        tweets.add(tweet1);
        Timespan ts = Extract.getTimespan(tweets);
        assertEquals(d1, ts.getStart());
        assertEquals(d2, ts.getEnd());
    }

    /** cover n = 0, n = 1, n >= 2 and user name case-insensitive*/
    @Test
    public void testNumberOfMentionedUser(){
        List<Tweet> tweets = new ArrayList<>();
        Set<String> mentionedUserNames = new HashSet<>();

        // n == 0
        tweets.add(tweet0);
        mentionedUserNames = Extract.getMentionedUsers(tweets);
        assertTrue(mentionedUserNames.isEmpty());

        //n == 1
        tweets.add(tweet1);
        mentionedUserNames = Extract.getMentionedUsers(tweets);
        assertTrue(mentionedUserNames.contains("sb"));

        //n >= 2
        tweets.add(tweet3);
        mentionedUserNames = Extract.getMentionedUsers(tweets);
        assertTrue(mentionedUserNames.contains("sb"));
        assertTrue(mentionedUserNames.contains("haha"));

        assertEquals(2, mentionedUserNames.size());
    }

    @Test
    public void testSpecialUserName(){
        List<Tweet> tweets = new ArrayList<>();
        Set<String> mentionUserNames = new HashSet<>();
        tweets.add(tweet2);
        mentionUserNames = Extract.getMentionedUsers(tweets);
        assertTrue(mentionUserNames.isEmpty());
    }
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGetTimespanTwoTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2));
        
        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d2, timespan.getEnd());
    }
    
    @Test
    public void testGetMentionedUsersNoMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet0));
        
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * Extract class that follows the spec. It will be run against several staff
     * implementations of Extract, which will be done by overwriting
     * (temporarily) your version of Extract with the staff's version.
     * DO NOT strengthen the spec of Extract or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Extract, because that means you're testing a
     * stronger spec than Extract says. If you need such helper methods, define
     * them in a different class. If you only need them in this test class, then
     * keep them in this test class.
     */

}
