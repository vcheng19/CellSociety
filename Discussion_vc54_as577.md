##Refactoring Refactoring Discussion
=======================================

For the Code Smell Refactoring section, we refactored the Rule Enforcer and GridInitializer to remove that duplicated code. This way we would not need to loop through the entire grid in each subclass to cast each cell individually. This is more to lead to a refactored and more general Cell class that was started but not completely finished. The point of refactoring the Cell class would be so it has methods used by each subclass so that we can get rid of the casting, because currently each cell class has its own methods so we have to cast them in the RuleEnforcers and the GridInitializer. The Cell refactoring was started with the checkStatus() method that the super class and all the classes should have and that method should make the necessary call to see if its alive or dead, red or blue, etc.

We also fixed the checklist by making all ArrayLists general lists and getting rid of the public variables and adding getters and setters instead. The purpose of this would be to make the code more flexible and limit the amount of data other classes outside the subclass can change.
