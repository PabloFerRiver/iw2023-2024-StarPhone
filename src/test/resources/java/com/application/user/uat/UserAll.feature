Feature: User Management

  Scenario: Create a new user
    Given I have a new user data
    When I create a new user
    Then the user should be stored in the database

  Scenario: Update a user's details
    Given I have an existing user
    When I update the user's details
    Then the updated details should be stored in the database

  Scenario: Delete a user
    Given I have an existing user
    When I delete the user
    Then the user should be removed from the database

  Scenario: Retrieve a user's details
    Given I have an existing user
    When I retrieve the user's details
    Then the correct details should be returned

  Scenario: User login
    Given I have an existing user
    When the user logs in with correct credentials
    Then the user should be granted access

  Scenario: User login with incorrect credentials
    Given I have an existing user
    When the user logs in with incorrect credentials
    Then the user should not be granted access