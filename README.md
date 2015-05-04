# iTunes Store Viewer
iTunes Store Viewer is a mobile app the streams information from [iTunes’s RSS Feed] and [iTunes's Search API], and displays store items on an android device. These feeds automatically update to reflect iTunes' top charts and content, so this app will always be up to date with the latest in the iTunes Store.The app currently shows top 200 items from eight categories:
- Top Grossing Mobile Applications
- Top Grossing Mac Applications
- Top Songs
- Top Albums
- Top Movies
- Top TV Episodes
- Top Books
- Top Podcasts

###Features
####Sliding Nagivation Mobile
The app’s home page displays the top 200 items in eight categories. Notice how each category is associated with a specific color.

![SlidingM](http://i.imgur.com/yqYZMLt.gif)
####Sliding Nagivation Tablet
For larger screens, the app displays the items list along side the item details.
![SlidingT](http://i.imgur.com/GwHkKZQ.gif)
####Viewing Media Details
Selecting an item delays that item’s details (i.e. price, description, release date, author, etc.). When a mobile app or mac app is selected, screenshots of the app are displayed at the bottom of the description.
![Details](http://i.imgur.com/nOxStKu.gif)
####iTunes Redirect
At the bottom of every item description, there is a “Get it on iTunes” badge (or “Get it on iBooks” badge for books). Clicking on the badge will redirect the user to iTunes where he/she can purchase the selected media.
![iTunesRedirect](http://i.imgur.com/l5jgo8J.gif)
####Search
With help from the iTunes Search API, users can type the name of content in the search window at the top and view associated content across all categories.

![Search](http://i.imgur.com/ha9Sh99.gif)
####Favoriting
Clicking the heart icon at the top of each item's description will add that item to the user’s favorites. The favorites list is located in the Navigation Drawer and these items will remain there even after the app closes.
![Favoriting](http://i.imgur.com/PgpbYo3.gif)
####Sharing
Clicking on the share icon at the top of the item description will allow users to share an iTunes link to another app.
![Sharing](http://i.imgur.com/eX1V1vw.gif)
####Play Store Search
Clicking on the play store icon at the top of the item description will redirect the user to the Play Store. Once there, the app search for the associated content, which may or may not be in the Play Store.
![Play Store](http://i.imgur.com/A5tmUff.gif)

###Version
1.0
###Known Issues
-Memory leaks may occur after extend use of the app. For now, restarting the app clears memory and it can continue to be used normally.

###To do
-Add better handling for network errors
-Highlight list items on press
-Organize variable and add comments

###Acknowledgments
- Thanks to Google Development teams for the [Volley] library, [GSON] library, and a comprehensive example on [SlidingTabLayouts].
- Thank to [Joe Littlejohn] for creating [jsonschema2pojo], a tools that can generate Java types from JSON Schema.

###Developed By
Darien Nurse – [darienurse@gmail.com]

[<img src="http://i.imgur.com/uMKQoqL.png">](https://plus.google.com/+DarienNurse/posts)[<img src="http://i.imgur.com/s7KNPyo.png">](www.linkedin.com/pub/darien-nurse/26/623/a55/en)[<img src="http://i.imgur.com/meZkHZ2.png">](https://twitter.com/darienurse)

[Joe Littlejohn]:https://github.com/joelittlejohn
[jsonschema2pojo]:http://www.jsonschema2pojo.org/
[GSON]:https://code.google.com/p/google-gson/
[Volley]:http://developer.android.com/training/volley/index.html
[SlidingTabLayouts]:https://developer.android.com/samples/SlidingTabsBasic/project.html
[Itunes’s RSS Feed]:https://rss.itunes.apple.com/us/]
[iTunes's Search API]:https://www.apple.com/itunes/affiliates/resources/documentation/itunes-store-web-service-search-api.html]
[darienurse@gmail.com]:darienurse@gmail.com
