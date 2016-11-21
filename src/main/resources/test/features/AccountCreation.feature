Feature: Account Creation Feature
  As a QA team aspirant
  I want to test neo creation account
  So I know the login requisites work.

  Background:
    Given Im on neo account creation page

  Scenario: empty email
    When I set a/an emptyEmail and correctPassword and create account button is pushed
    Then emptyEmail error is shown

  Scenario: invalid email
    When I set a/an invalidEmail and correctPassword and create account button is pushed
    Then invalidEmail error is shown

  Scenario: empty password
    When I set a/an correctEmail and emptyPassword and create account button is pushed
    Then emptyPassword error is shown

  Scenario: invalid password
    When I set a/an correctEmail and invalidPassword and create account button is pushed
    Then invalidPassword error is shown

  Scenario: invalid captcha
    When I set an invalidCaptcha
    And I set a/an correctEmail and correctPassword and create account button is pushed
    Then Captcha error is shown
