![alt tag](https://github.com/BigDataForSanDiego/team-WaterSavers/blob/master/banner.jpg)

# Water Savers



## Inspiration :

I am passionate about California's drought and it's implications on citizens. The new water restrictions have made many residents aware that they may have to change their water consumption, especially because there are fines involved. However, many residents do not know if they are using too much water or what they should do to conserve. In this app I want to give people a clear sense of how much water they are using relative to other users and and what they should be doing to conserve.

## What it does :

This is a mobile app that meant to educate San Diego County residents about how to conserve water and understand why conserving water is essential to our quality of life. The app asks the user a number of questions about how much water they consume and then ranks them relative to other residents in the county. Based on the users inputs, the app also generates a short list of water saving tips.

User-Supplied Data -- Questions that the user will answer in order (user inputs):

Location (zipped or auto-generated by app)
How many people live in your house? (this should be used to generate flushes, meals, showers)
How many showers do members of your household take each day? How long are your showers?
Do you have a low-flow shower head?
Do you have a lawn? How big is your lawn?
Do you use a dishwasher, a washer and a drier? If so, how often do you use them? -----This will generate the Per Capita current water usage of the user.

Comparison Data:

Per Capita Water Use Data for California: http://waterdata.usgs.gov/ca/nwis/wu (used to create the comparison)
Per Capita Water use in San Diego. Source Equinox Project /San Diego Regional Water Authority : http://www.equinoxcenter.org/regional-dashboard/interactive-dashboard/water-consumption.html (used to create the comparison)
Information on lawn requirements. Taken from railbird website. This will generate an estimate of water used on a lawn.
Information on appliances: How much water do the appliances use? These will be estimates based on when the appliances were made.
Shower Output (Older Showers v. Low-flow shower heads). Estimates based on when shower heads were installed. -------This comparison data will be compared to the user's per capita usage to generate a score.
Outputs:

Water usage compared with other people in the same area. How much water can be saved by making changes (landscape/lifestyle changes). Strategies for saving water.

##Features : 
1) Login with username password, if not registered signup.  
2) Take a short survey to answer questions related to your water usage. Hints are also available to help you answer certain questions.  
3) Once you complete the survey, you will receive score based on your water conservation. Score is generated based on a formula which was formulated using data available from water agencies in San Diego.  
4) You can share your score by posting it on facebook, using facebook share button.  
5) You can also map yourself and see the scores of people in and around your locality.  
6) Based on your response, you will be provided with hints to conserve water and score more.  
7) In order to gamify water conservation, individuals will be given gift coupons if their score crosses some milestones. you can view the milestones, and the associated coupons under 'Goals'.  
8) Also, you can view the leaderboard to compare your scores with others!  
8) Once done, you can sign out easily.  

## Screenshots :
![alt text](https://github.com/satiaarpit/WaterSavers/blob/master/WaterSavers-screenshots.jpg)

##Note :
1)In order to signup, your device GPS must be on, which is required to take location parameters.  
2) Works best in 5 inch screen size.  






## Challenges I ran into :

No APIs available, so I had to reformat data. The original idea needed to be paired down based on what data was available.




## Accomplishments that I'm proud of :

Finding sources with relevant water data. There was a lot of broad water data that was not relevant to our app. Additionally, It was good to come up with a flow for the user interface that was simple and provided us with enough data to give an accurate ranking.





## What's next for Water Savers :

The app can be used to suggest alternate landscaping options and/or appliances.

 Also, thinking of merging water bill with the application for authentication

## Technologies and Sdk used :


* Android SDK   
* Facebook SDK  
* Google Android API   
* Android Studio   
* RESTFul APIs
(for login,register and maintaining user profiles)  
