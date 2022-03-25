# Finants

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
Tracks user's finance goals to promote saving habits. Users share progress and goals to create a supportive environment.

### App Evaluation
- **Category:** Finance/Social
- **Mobile:** This app is primarily being built for android devices. It could potentially be a web app in the future to make it more scalable and accessible.
- **Story:** Users can set financial goals to save money and improve their money-management skills. Users can promote saving habits within the community by meeting their goals and sharing them with the community.
- **Market:** Anyone who wants to manage their finances can use this app.
- **Habit:** Users can use this app whenever they have a financial event to track.
- **Scope:** First we would promote savings skills by sharing users' successful goals. Eventually this app could evolve into a social media app highlighting users' financial achievements.

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* User logs in to access profile and feed
* User can register to make an account
* User can set financial goals
* User can set times to reach these goals and/or repeaters for these events
* Main feed with everyone's public goals 
* Profile to see your own goals


**Optional Nice-to-have Stories**

* Calendar for better time and money management
* Friends list
* Isolating posts from friends

### 2. Screen Archetypes

* Login
* Register
    * Upon downloading the app, user is prompted to log in/register again
* Profile
    * User will be able to see all goals and overall timeline in an organized manner
* Compose
    * User can manage money goals with descriptions, images, private and public settings, and repeating options
* Feed
    * Users can see all people's public goals
    * [OPTIONAL] Users can isolate posts from friends in a private feed
* [OPTIONAL] Calendar
    * Easily manage goals in a scheduled manner

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Feed
* Compose
* Profile
* [OPTIONAL] Calendar

**Flow Navigation** (Screen to Screen)

* Forced-Log-in
   * Register for new account if there is no log in available
* Feed
   * Find goals from everyone
* Compose
   * Options to display and organize financial goals
* Profile
    * Only goals sent from your account are visible

## Wireframes
<img src="https://github.com/CodePath-Android-Final/Finants/blob/main/Finants.jpg" width=600>


## Schema 
[This section will be completed in Unit 9]
### Models
**POST**

| Property | Type | Description |
| ---- | ---- | ----|
| objectId | String | unique id for user post(default field) |
 author| Pointer to User | image author 
 image | File | image that user posts
 description | String | description of goal
 visibility | Boolean | Determines whether post is visible
 commentsCount | Number | number of comments
 likesCount | Number | number of likes
 createdAt | DateTime | date when post is created (default field)
 updatedAt | DateTime | date when post is last updated (default fied)
 
 
**USER**

| Property | Type | Description |
| ---- | ---- | ----|
| objectId | String | unique id for user(default field) |
posts | Array | array of pointers to posts
 



### Networking
#### List of network requests by screen
- Home Feed Screen
  - (Read/GET) Query all posts from multiple users
```
	// Specify which class to query
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        // Find all Post objects
        query.include(Post.KEY_USER)
        //Return the posts in descending order: ie newer posts will appear first
        query.addDescendingOrder("createdAt")

        query.findInBackground(object : FindCallback<Post> {
            override fun done(posts: MutableList<Post>?, e: ParseException?) {
                if (e != null) {
                    //Something went wrong
                    Log.e(TAG, "Error fetching posts")
                } else {
                    if (posts != null) {
                        allPosts.addAll(posts)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        })
```
   - [Reach Stories for Home Feed Screen]
     - (Create/POST) Create a new like on a post
     - (Delete) Delete existing like
     - (Create/POST) Create a new comment on a post
     - (Delete) Delete existing comment

- Information Screen
  - (Read/GET) Query all expenses where "author" is user
  - (Read/GET) Query most recent expense where "author" is user
  - (Read/GET) Query all income where "author" is user
  - (Read/GET) Query most recent income where "author" is user
  - (Read/GET) Query all goals where "author" is user
  - (Read/GET) Query most recent goal where "author" is user
  - (Update/PUT) Update total money saved
  - (Update/PUT) Update the savings, income, expense and recurring fees monthly

- Profile Screen
  - (Read/GET) Query logged in user object
  - (Update/PUT) Update user profile image
  - (Create/POST) Create a new goal
  - (Create/POST) Create a new income
  - (Create/POST) Create a new expense
  - (Update/PUT) Update existing goal
  - (Update/PUT) Update existing income
  - (Update/PUT) Update existing expense

## User Stories

The following **required** functionality is completed:

- [X] Users can create an account
- [X] Users can login with existing accounts
- [X] Added app icon
- [X] User authentication with back4app backend
- [X] Bottom Navigation Bar created
## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://github.com/CodePath-Android-Final/Finants/blob/main/walkthrough.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).
