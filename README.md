Original App Design Project - README Template
===

# FindGroupEat

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
An app that allows the user to group up with 1-6 other users and search nearby restaraunts, you swipe if you like the restaurant and if all users of the group swipe on the same restaurant then that will be where you will eat.

### App Evaluation
[Evaluation of your app across the following attributes]
- **Category: Social networking**
- **Mobile: The app would be best for mobile but it could easily be created in JS/React.**
- **Story: User creates an account and can group up with 1-6 other users in a group where they can search nearby restauraunts in a 5-20 mile radius. When all users swiped on a restauraunt that would be the restauraunt they would go to.**
- **Market: This app is viable for anyone.**
- **Habit: Whenever an individual cannot decide what they want to eat or maybe they just want to make sure that everyone is happy with the restauraunt choice.**
- **Scope: Global, depending on if we implemented ML language translations or some other kind of language translation so every country could use it.**

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* User can create an account and login
* Users can create a group
* Users can swipe on nearby restauraunts
* User can click on restauraunt and see details such as type of food they serve, menu, more pictures of inside, outside, reviews, etc.



**Optional Nice-to-have Stories**

* Profile page (Could contain past restauraunts matched with and users rating of said restauraunt)
* Settings page
* Implement reviews possibly (when clicking on a restaurant
* Filter out certain restauraunts user does not want to see (user is vegan, group wants pizza, etc)
* User chat



### 2. Screen Archetypes

* Login/ Register - User can register and login
   * User will need to create an account and login before entering the app.
   * ...
* Group Screen
   * Main page where user enters a username to add to group 1-6 max and then user clicks a button to begin searching nearby restauraunts
* Restauraunts swiping page
   * Allows the group to begin swiping and when a match is found perhaps either the group can decline or accept if they wish to. Resets back to group screen after restauraunt is found

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Group page
* Profile
* Settings

**Flow Navigation** (Screen to Screen)

* Login and register - > group page
* Group screen when n group is formed which is n >= 1 && n <= 6
* Restauraunts swiping page - When all users swipe on the same restauraunt a match is found and users can possibly decline or accept match -> back to group page

## Wireframes
<img src="https://i.imgur.com/skfL3hq.png" width=600>

### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Schema

### Models
#### USER:
| Property| Type   | Description |
| --------| -------| ------------|
| userID  | Long   | userID for Firebase or SQL |
| name    | String | user name   |
| email   | String | user email |
| password| String | user password |
| picture | String | user profile picture (optional) |

#### RESTAURANT:
| Property| Type   | Description |
| --------| -------| ------------|
| restaurantID| Long   | ID for restaurant |
| name        | String | restauraunt's name |
| description | String | restauraunt's description |
| photo       | String | photo URL of restaurant |
| tags        | ArrayList<String> | Restaurant's tags (Ex: Italian, vegan) |

#### GROUP:
| Property| Type   | Description |
| --------| -------| ------------|
| groupID | Long   | ID for group |
| usersInGroup | ArrayList<User> | users in the group |


### Networking
#### List of network requests by screen
- Login/Register Screen
    * (Create/POST) Create a user object
- Group Screen
    * (Create/POST) Create a group of users who will swip for restaurants
    * (Update/PUT) Update the group information
- Profile Screen
    * (Read/GET) Get all information of the current logged in user
    * (Update/PUT) Update the user information
- Restauraunts swiping page
    * (Read/GET) Query a list of restaurants based on the group information
