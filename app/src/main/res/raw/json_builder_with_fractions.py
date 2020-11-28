#!/usr/bin/env python
# -*- coding: utf-8 -*-
import re
import json

# {
#   "title" : {
#     "directions": "string",
#     "recipeIngredients" = {
#       "raw_ingredient_string": ["ingredient"]
#     },
#     "categories": ["category"]
#   },
#   "title" : {
#     ...
#   },
#   ...
# }
theRecipes = {"zzzztotal": 0}

recipesFile = open('recipes.xml','r')
ingredientsFile = open('ingredients.txt', 'r')

ingredientsString = ingredientsFile.nextLine()
print(ingredientsString)


shouldCapture = False
isRecipe = False
hasIngredients = False
hasDirections = False
hasCategory = False
hasDescription = False
numberOfRecipes = 0
numberWithCategories = 0
fractions = {'½': '.5', '⅓':'.334', '⅔':'.667', '¼':'.25', '¾':'.75', '⅕':'.2',
             '⅖':'.4', '⅗':'.6', '⅘':'.8', '⅙':'.167', '⅚':'.834', '⅐':'.143',
             '⅛': '.125', '⅜': '.375', '⅝':'.625', '⅞':'.875', '⅑':'.111',
             '⅒':'.1'}
addThese = []

for line in recipesFile:

    text = line.strip()

    categories = []
    if '<title>' in text:
        title = text
        title = title.replace('<title>', '').replace('</title>', '')
        shouldCapture = True
    if re.search(r'[Cc]ategory', text):
        hasCategory = True
    if re.search(r'[Dd]escription', text):
        hasDescription = True
    if re.search(r'[Ii]ngredient', text):
        hasIngredients = True
    if re.search(r'[Dd]irection', text):
        hasDirections = True

    if '</page>' in text:
        # May include recipes having hasDescription, hasIngredients,
        #                            hasDirections,  hasCategory
        if hasIngredients and hasDirections and hasDirections:
            """ Leave fractions in for now.
            for i in range(len(addThese)):
                for key, value in fractions.items():
                    if key in addThese[i]:
                        addThese[i] = re.sub(key, fractions[key], addThese[i])
            """
            recipeDict = {}
            recipeIngredients = {}
            directions = []
            categories = []
            atCategory = False
            step = 1
            atDirections = False
            for addLine in addThese:
                text = addLine.strip().lower()
                if not atDirections:
                    if re.search(r'[Dd]irections', text):
                        atDirections = True
                    else:
                        if re.search(r'\[\[[a-z]+[|]?[a-z]*\]\]'):
                            
                        else:
                            break
                        ingredients = []
                        hasIngredients = False
                        for i in ingredientsList:
                            if i.replace('\n', '') in text:
                                ingredients.append(i.strip())
                                hasIngredients = True
                        if hasIngredients:
                            recipeIngredients[text] = ingredients
                        else:
                            recipeIngredients[text] = "NO"
                elif not atCategory:
                    if re.search(r'[Cc]ategory', text):
                        atCategory = True
                    else:
                        directions.append(text)
                else:
                    if "[[File" not in text and "Videos" not in text:
                        text = re.sub(r"<.*>", "", text)
                        text = re.sub(r"'''", "", text)
                        category = re.sub(r'\[\[Category:', '', text)
                        category = re.sub(r'\]\]', '', category)
                        categories.append(category)
        if directions != "" and recipeIngredients != {} and categories != []:
            theRecipes[title] = {"directions": directions, "recipeIngredients": recipeIngredients, "categories": categories}
        title = ""
        addThese = []
        shouldCapture = False
        hasDescription = False
        hasIngredients = False
        hasDirections = False
        hasCategory = False
        atCategory = False
        ingredients = []
        theRecipes["zzzztotal"] += 1
        print(theRecipes["zzzztotal"])
    if hasDescription:
        if "[[File" not in text and "Videos" not in text:
            text = re.sub(r"<.*>", "", text)
            text = re.sub(r"'''", "", text)
            if 'Category:' in text:
                category = re.sub(r'\[\[Category:', '', text)
                category = re.sub(r'\]\]', '', category)
                categories.append(category)
            if '</text>' in text:
                text = text.replace('</text>', '')
            addThese.append(text)

recipesFile.close()
ingredientsFile.close()

with open('recipeJSON.json', 'w') as fp:
    json.dump(theRecipes, fp, sort_keys=True, indent=4)

print(ingredientsList)
