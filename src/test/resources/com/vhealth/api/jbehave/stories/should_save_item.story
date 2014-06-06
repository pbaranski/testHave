Narrative:
In order to add my health event
As a logged user of vHealth
I want save my health problem

Scenario:  Save item with disease details

Given user testUserDetails in database
When I save item with details of disease: <DISEASE>
Then the item with given disease: <DISEASE> should be available in application

Examples:
|   DISEASE             |
|   flu                 |
|   stomachache & fever |
