Feature: Tasks

Background: Launch the browser
  Given user is on Test page

Scenario: Show Rows dropdown filter
  When user Shows 50 rows
  Then user should see 50 entries

Scenario: Show filters records using Filter button
  When user filter MarketCap with range "$1B - $10B"
  And user filter Price with range "$101 - $1,000"
  Then user should see filter records by Market "$1B - $10B" and Price "$101 - $1,000"
