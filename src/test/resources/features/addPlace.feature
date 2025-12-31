Feature: Add Place API

  Scenario: Verify Add Place API
    Given Add place payload
    When User calls Add Place API with POST request
    Then API call is successful with status code 200
    When User calls Get Place API with GET request
    Then Get Place API response should match the added place
    When User calls Delete Place API with POST request
    Then Delete Place API response should be successful
    Then Get Place API should return error for deleted place