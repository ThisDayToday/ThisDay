# ThisDay

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
ThisDay is an app dedicated to displaying free, cheap, and current events happening around a user's area. The user will be suggested events related to their interests, be able to check-in and share events they are attending, and have the ability to add their friends and see their recent or upcoming events.

## Product Spec
### 1. User Stories (required and optional)

**Required Must Have Stories**

- [x] User can Login & Register
    - [ ] User takes quick interest quiz when first registering
- [x] User can view feed 
- [ ] User can view event detail activity
- [ ] User can check-in to events through feed or event detail
- [ ] User can share events
- [x] User can create their own event
- [x] User has access to their profile
    - [ ] User can edit their location, profile picture, username
    - [ ] User profile contains check-in events
- [ ] User can check-in to an event
- [ ] User can Logout
- [ ] User can view events while not connected to internet (persistence)

**Issues to be completed before implementing User Stories**
- [x] Set up Back4App database
- [x] Set up XML layouts
- [x] Begin connecting XMLs to classes 

**Optional Nice to Have Features**

* Most popular (trending)
* Recommended
* Map of the event location


### 2. Screen Archetypes
<img src='https://imgur.com/UtKJfK9.gif' title='Video Walkthrough' width='350' alt='Video Walkthrough' />

* Login
    * User can login with username/email and password.
* Register
    * User signs up with unique username and email
* Stream
    * User can see recommended events and friend's events
* Profile
    * Shows events attending/attended and interests
* Creation
    * User can create an event 
* Detail
    * Users can see a detail view of an event
* Search
    * Users can search for an event


### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Recommended Feed
* Create Event
* Profile

**Flow Navigation** (Screen to Screen)

* Register
    * Information intake
    * Interest Quiz
    * Recommended Feed
* Recommended Feed
    * Event Detail
        * Event Organizer Profile
    * Search
        * List of results
* Friend Feed
    * Event Detail
        * Event Organizer Profile
* Profile
    * Edit Information
    * Event Detail
    * Interests
    * Create Event

## Wireframes

<img src="https://i.gyazo.com/32230d47760a0732e78b1ab8a632d61a.png" width=400>

### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype


## Schema 
## Models
### User
| Property Name | Type | Description |
| --- | --- | --- |
| objectId | String | Unique id for the user post (default) |
| username | String | Unique name for the user |
| location | String | User’s location provided at registration |
| password | String | Unique user password |
| email | String | Unique user email |
| phone | String | User’s phone number |
| friends | (User)Array | List of friends added by the user |
| recentEvents | (Event)Array | List of recently attended events |
| friendsEvents | (Event)Array | List of events the user plans on attending |
| profileImg | File | Header image for user profile |
| userImg | File | User profile display image |

### Event
| Property Name | Type | Description |
| --- | --- | --- |
|objectId| String | Unique id for the event post.|
| eventName | String | Name of the event|
|description|String|Description of event|
|date|Date|Date of event|
|createdAt|Date|Date of created event|
|attending|Int|Number of people attending event|
|eventLocation|String|The location of event|
|update|Date|Date update event|
|friendsAttending|(User)Array|List of all of the user’s friends attending|
|eventImage|File|Image of the event|
|eventType|String|The type of event|

### Networking

**Homepage**
* No network request action, navigate to login or register.

**Register**
* (Create/POST)
     * Creating and saving a new user.

Example Code:
```java
private void savePost(String description, ParseUser currentUser, File photoFile) {
   Post post = new Post();
   post.setDescription(description);
   post.setImage(new ParseFile(photoFile));
   post.serUser(currentUser);
   post.saveInBackground(new SaveCallback() {
       @Override
       public void done(ParseException e) {
           if(e!=null){
               Log.e(TAG,"Error while saving",e);
               Toast.makeText(getContext(), "Error while saving!",Toast.LENGTH_SHORT).show();
           }
           Log.i(TAG,"Post save was successful");
           etDescription.setText("");
           ivPostImage.setImageResource(0);
       }
   });
}
```

# **Feeds Activity**
* (Read/GET)
* Fetching posts for a user’s feed

## Example Code:
```java
public void bind(Post post) {
  tvDescription.setText(post.getDescription());        // Get Caption for a post
  tvUsername.setText(post.getUser().getUsername()); // Get username for a post

  ParseFile image = post.getImage();	// Get image for a post
  if(image!= null){
      Glide.with(context).load(post.getImage().getUrl()).into(ivImage);
  }
```

# **User Profile**
* (Read/GET)
*Fetch all of this user’s posts
* (Delete)
*Delete an existing post

## Example Code:
```java
public void bind(Post post) {
  tvDescription.setText(post.getDescription());        // Get Caption for a post
  tvUsername.setText(post.getUser().getUsername()); // Get username for a post

  ParseFile image = post.getImage();	// Get image for a post
  if(image!= null){
      Glide.with(context).load(post.getImage().getUrl()).into(ivImage);
  }
```
# **Event Detail**
* (Read/GET)
* Fetch details on a selected post

## Example Code:
```java
public void bind(Post post) {
  tvDescription.setText(post.getDescription());        // Get Caption for a post
  tvUsername.setText(post.getUser().getUsername()); // Get username for a post

  ParseFile image = post.getImage();	// Get image for a post
  if(image!= null){
      Glide.with(context).load(post.getImage().getUrl()).into(ivImage);
  }
```
