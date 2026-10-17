# Meal Planner

A Java desktop meal planner that turns the ingredients you have into recipes, tracks nutrition, and schedules your week of meals. Built with Clean Architecture for CSC207 (Software Design) at the University of Toronto.

> **Project status:** early-stage. The architecture, entities, use case boundaries, and test scaffolding are in place, and a few classes are fully implemented (for example `Ingredient`, `NutritionInfo`, `NutritionGoals`, `Unit`, `MealType`, `DietaryRestriction`). Most interactors, views, and API clients are still stubs with TODO markers, so the app does not launch yet.

## What and why

Planning meals usually means juggling recipe sites, a calendar, and a calorie counter. This project brings those together: enter what is in your fridge, see matching recipes with nutrient info, save your own recipes, adjust serving sizes, and drop meals into a weekly schedule.

## Features

- Search recipes by the ingredients you already have
- Browse recipes and see the ingredients and quantities they need
- Create and save your own recipes, with nutrition calculated from the ingredients
- Plan breakfast, lunch, and dinner across the week (add, edit, delete)
- Scale a recipe to a different serving size
- Log in by username to see your saved recipes and schedule

## How it works

The code follows **Clean Architecture** in four layers, with dependencies pointing inward:

| Layer | Package | Contents |
|-------|---------|----------|
| Entities | `entity` | `Recipe`, `Ingredient`, `User`, `Schedule`, `MealPlan`, `NutritionInfo`, `NutritionGoals`, and the `Unit`, `MealType`, `DietaryRestriction` enums |
| Use cases | `use_case` | One package per feature, each with an interactor plus input and output boundaries |
| Interface adapters | `interface_adapter` | Controllers, presenters, and view models |
| Frameworks and drivers | `view`, `data_access`, `config` | Swing views, Spoonacular and Edamam API clients, JSON file storage |

Each use case defines its own data access interface, so interactors never depend on the API or file code directly. Custom exceptions (`RecipeNotFoundException`, `InvalidServingSizeException`, and others) keep error handling explicit.

## Tech stack

- Java 11, Maven
- Gson for JSON, OkHttp for HTTP calls
- JUnit 5 and Mockito for tests
- SLF4J and Logback for logging
- [Spoonacular API](https://spoonacular.com/food-api) and [Edamam Meal Planner API](https://developer.edamam.com/edamam-docs-meal-planner-api) for recipe and nutrition data

## Quick start

Prerequisites: Java 11 or higher, Maven 3.6+, and API keys from Spoonacular and Edamam.

```bash
git clone https://github.com/aaryanp35/CSC207-Project-Meal-Planner.git
cd CSC207-Project-Meal-Planner

# Add your API keys (this file is git-ignored)
cp config/api_keys.properties.example config/api_keys.properties
# then edit config/api_keys.properties

mvn clean install      # build and run tests
mvn exec:java -Dexec.mainClass="com.mealplanner.app.Main"
```

Heads up: `mvn compile` currently fails on `main` because the repository classes call getters that the `Recipe` stub does not have yet, and `Main` is still a TODO. Both are expected for this stage of the project.

## Project structure

```
src/main/java/com/mealplanner/
├── app/                # Main, AppBuilder, UseCaseFactory (dependency wiring)
├── entity/             # Domain objects and enums
├── use_case/           # Interactors and boundaries, one package per feature
├── interface_adapter/  # controller, presenter, view_model
├── data_access/        # api (Spoonacular, Edamam) and database (JSON files)
├── repository/         # Repository interfaces and file-based implementations
├── view/               # UI screens and components
├── config/             # ApiConfig, AppConfig, ConfigLoader
├── exception/          # Domain-specific exceptions
└── util/               # Nutrition math, unit conversion, validation helpers
src/test/java/...       # Mirrors main, one test class per component
data/                   # Local JSON storage (git-ignored except its README)
config/                 # API key template
```

## User stories

1. Enter ingredients and get a recipe for my next meal, with its nutrient intake.
2. See which ingredients I need to buy for the recipes I plan for next week.
3. Save a favorite meal with ingredients, quantities, and steps, and get its nutrition.
4. Add, edit, and delete my meal plan for the next few days.
5. Adjust serving sizes to make the right portions.
6. Enter my username and see my schedule and saved recipes.

## Team and work split

**Group 14, TUT0201:** Aaryan Patel, Eden Chang, Grace Lin, Jerry Chai, Mona El Yass, Regina He.

| Lead developer | Use case |
|----------------|----------|
| Jerry | Enter ingredients, receive recipe |
| Regina | Enter recipe, receive ingredients |
| Aaryan | Storing a recipe |
| Grace | Manage meal plan |
| Eden | Adjust recipe serving size |
| Mona | Login and browse user schedule |
| Everyone | GUI, API integration, data access, entities, testing, documentation |

Full timeline: [Notion work tracker](https://maddening-year-2cf.notion.site/Meal-Planner-Project-Timeline-2a1e3eca9d8080409a2dcb7f40ee79e5). Team guidance on AI tools is in [AI_USAGE_GUIDELINES.md](AI_USAGE_GUIDELINES.md).

## My role (Aaryan Patel)

- Lead developer for the **Store Recipe** use case (user story 3); assigned its interactor, boundaries, controller, presenter, view model, and view
- Implemented the immutable `Ingredient` entity with input validation, `withQuantity`, `equals`, `hashCode`, and `toString`
- Assigned `Unit`, the file-based recipe data access, and several domain exceptions

<!-- TODO (Aaryan): add 2 or 3 outcome-focused bullets once more of Store Recipe is implemented (what you built, what you tested, what you learned). -->

## What I learned

- Applying Clean Architecture to a real feature: boundaries, dependency inversion, and keeping interactors free of UI and API details
- Designing immutable, validated entities that fail fast on bad input
- Planning a multi-person codebase by agreeing on interfaces and ownership before writing logic

## Author

**Aaryan Patel**, 3rd-year Economics (Data Analytics) student at the University of Toronto, with minors in Computer Science and Statistics.

- Email: aaryanp35@gmail.com
- LinkedIn: https://www.linkedin.com/in/aaryan-p-5a4223331/
- Portfolio: https://aaryanp35.vercel.app

Originally developed in a team repository by rhea4316's group. This is Aaryan's fork. Licensed for educational purposes as part of CSC207 at the University of Toronto.
