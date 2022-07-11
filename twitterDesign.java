class Twitter {
LinkedList<int[]> tweets; // The collection includes all tweets.
HashMap<Integer, HashSet> userList; //The collection includes all users and their following list.

/** Initialize your data structure here. */
public Twitter() {
    //Initialize two global structures
    tweets = new LinkedList();
    userList = new HashMap();
}

/** Compose a new tweet. */
public void postTweet(int userId, int tweetId) {
    //add a tweet to the global linkedList
    tweets.addFirst(new int[] {userId, tweetId});
}

/** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
public List<Integer> getNewsFeed(int userId) {
    if(!userList.containsKey(userId)) { userListInit(userId); }
    
    //get user's following list and creat a list to store the result.
    HashSet<Integer> followlist = userList.get(userId);
    List<Integer> feed = new ArrayList();
    int count = 10;// we only retrieve 10 tweets.
    
    //Traversal all the tweets in the linkedlist, check if their username in he following list.
    //if so, add to result. until get 10 results.
    for(int[] t : tweets) {
        if (count <= 0) {break;}
        if(followlist.contains(t[0])) {
            feed.add(t[1]);
            count--;
        }
    }
    
    return feed;
}

/** Follower follows a followee. If the operation is invalid, it should be a no-op. */
public void follow(int followerId, int followeeId) {
    if (!userList.containsKey(followerId)) { userListInit(followerId); }
    //get user's following list and add a new id in it.
    HashSet<Integer> followlist = userList.get(followerId);
    followlist.add(followeeId);
}

/** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
public void unfollow(int followerId, int followeeId) {
    if (!userList.containsKey(followerId)) { userListInit(followerId); }
    //get user's following list and remove an id from it.
    HashSet<Integer> followlist = userList.get(followerId);
    followlist.remove(followeeId);
}
//initialize a user's following list(add to global hashmap and following himself)
private void userListInit(int userId) {
    HashSet<Integer> following = new HashSet();
    following.add(userId);
    userList.put(userId, following);
}

}
