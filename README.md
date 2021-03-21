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

<img src="https://i.gyazo.com/32230d47760a0732e78b1ab8a632d61a.png" width=600>

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
