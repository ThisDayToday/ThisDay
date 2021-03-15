# ThisDay

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
* An interactive app that allows users to see what activities are happening around them and share what they are doing. Users are also able to see what their friends are up to.

## Product Spec
### 1. User Stories (required and optional)

**Required Must Have Stories**

* User can Login & Register
    * User takes quick interest quiz when first registering
* User can view feed 
* User can view event detail activity
* User can check-in to events through feed or event detail
* User can share events
* User can create their own event
* User has access to their profile
    * User can edit their location, profile picture, username
    * User profile contains check-in events
* User can check-in to an event
* User can Logout
* User can view events while not connected to internet (persistence)


**Optional Nice to Have Features**

* Most popular (trending)
* Recommended
* Map of the event location


### 2. Screen Archetypes

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
* Friends Feed
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
[Add picture of your hand sketched wireframes in this section]
<img src="YOUR_WIREFRAME_IMAGE_URL" width=600>

### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Schema 
[This section will be completed in Unit 9]
### Models
[Add table of models]
### Networking
- [Add list of network requests by screen ]
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]
