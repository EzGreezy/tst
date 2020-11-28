#!/usr/bin/env python
# -*- coding: utf-8 -*-
import re
import json
import enchant

d = enchant.Dict("en_US")

# {
#   "title" : {
#     "directions": "string",
#     "recipeIngredients" = {
#       "ingredient": ["raw_ingredient_string"]
#     },
#     "categories": ["category"]
#   },
#   "title" : {
#     ...
#   },
#   ...
# }
theRecipes = []

recipesFile = open('recipes.txt','r')

categoryBuilder = []
recipeBuilder = []
categoryMapBuilder = []
ingredientBuilder = []
recipeItemBuilder = []
itemIngredientMapBuilder = []

ingredientsSet = set([])
ingredientsIds = {}

try:
    while True:
        text = next(recipesFile).strip()
        if text == "==title==":
            while text != "==ingredients==":
                text = next(recipesFile).strip()
            while len(text) == 0:
                text = next(recipesFile).strip()
            while text[0] != "*":
                text = next(recipesFile).strip()
                while len(text) == 0:
                    text = next(recipesFile).strip()
            while len(text) < 3:
                text = next(recipesFile).strip()
            while text[0] == "*" or text[0:3] == "===":
                # TODO get sub ingredients later
                hasNone = False
                if text[0:3] == "===":
                    text = next(recipesFile).strip()
                    while len(text) < 3:
                        text = next(recipesFile).strip()
                else:
                    ingredients = re.findall(r'\[\[[a-zA-Z\uFFFF|]+\]\]', text)
                    if not ingredients:
                        hasNone = True
                        break
                    for i in ingredients:
                        splitIngredients = i.split('|')
                        for j in splitIngredients:
                            if not d.check(j):
                                hasNone = True
                            ingredientsSet.add(j)
                    text = next(recipesFile).strip()
                    while len(text) < 3:
                        text = next(recipesFile).strip()
except(StopIteration):
    ingredients = sorted(list(ingredientsSet))
    for i in range(1,len(ingredients)+1):
        ingredientsIds[ingredients[i-1].lower().replace('[[', '').replace(']]', '')] = i

for key, val in ingredientsIds.items():
    ingredientBuilder.append({"id": val, "name": key})
categoriesSet = set([])
categoriesIds = {}

recipesFile.seek(0)
try:
    while True:
        text = next(recipesFile).strip()
        if text == "==title==":
            while not re.search(r'\[\[[Cc]ategory\:[a-zA-Z]+ [Rr]ecipes\]\]', text):
                text = next(recipesFile).strip()
        while text != "==end==":
            d = False
            while re.search(r'\[\[[Cc]ategory\:[a-zA-Z]+ [Rr]ecipes\]\]', text):
                d = True
                cats = re.findall(r'\[\[[Cc]ategory\:[a-zA-Z]+ [Rr]ecipes\]\]', text)
                for cat in cats:
                    category = cat.lower()
                    category = category.replace('[[category:', '').replace(' recipes]]', '')
                    categoriesSet.add(category)
                text = next(recipesFile).strip()
            if not d:
                text = next(recipesFile).strip()
except(StopIteration):
    categories = sorted(list(categoriesSet))
    for i in range(1,len(categories)+1):
        if categories[i-1] not in ingredientsIds:
            categoriesIds[categories[i-1]] = i

for key, val in categoriesIds.items():
    categoryBuilder.append({"id": val, "name": key})

recipeId = 1
recipeItemId = 1


recipesFile.seek(0)
d = enchant.Dict("en_US")
try:
    while True:
        text = next(recipesFile).strip()
        title = ""
        directions = "<p><ol>"
        recipeIngredients = []
        ingredients = []
        categories = []
        recipeItemBuilderTemp = []
        itemIngredientMapBuilderTemp = []
        has = True
        if text == "==title==":
            title = next(recipesFile).strip()
            if title == "Pickled Cucumber":
              print title
            while text != "==ingredients==":
                text = next(recipesFile).strip()
            while len(text) == 0:
                text = next(recipesFile).strip()
            while text[0] != "*":
                text = next(recipesFile).strip()
                while len(text) == 0:
                    text = next(recipesFile).strip()
            while len(text) < 3:
                text = next(recipesFile).strip()
            while text[0] == "*" or text[0:3] == "===":
                # TODO get sub ingredients later
                if text[0:3] == "===":
                    text = next(recipesFile).strip()
                    while len(text) < 3:
                        text = next(recipesFile).strip()
                else:
                    ingredients = re.findall(r'\[\[[a-zA-Z\uFFFF]+\]\]', text)
                    if not ingredients:
                        has = False
                        itemIngredientMapBuilderTemp = []
                        recipeItemBuilderTemp = []
                        break
                    ingredientIDs = []
                    for j in ingredients:
                        splitIngredients = j.split('|')
                        for i in splitIngredients:
                            # TODO implement spell checker to consolidate ingredients
                            ing = i.lower().replace('[[', '').replace(']]', '')
                            if not d.check(ing):
                                has = False
                                break
                            itemIngredientMapBuilderTemp.append({"recipe_item_id": 0, "ingredient_id": ingredientsIds[ing]})
                    recipeItemBuilderTemp.append([{"id": 0, "recipe_id": 0, "description": text[2:]}, itemIngredientMapBuilderTemp])
                    text = next(recipesFile).strip()
                    while len(text) < 3:
                        text = next(recipesFile).strip()
                    itemIngredientMapBuilderTemp = []
            # FIXME don't add recipes that don't have at least 1 '#' in the directions part of the parsing
            if has:
                hasDirections = False
                for i in recipeItemBuilderTemp:
                    # recipeItemBuilder.append({"id": recipeItemId, "recipe_id": recipeId,
                    #                                               "description": "* " + i[0]["description"].replace('[[', '<font color="#FF4081">').replace(']]', '</font>')})
                    recipeItemBuilder.append({"id": recipeItemId, "recipe_id": recipeId,
                                              "description": "* " + i[0]["description"].replace('[[', '').replace(']]', '')})
                    for j in i[1]:
                        itemIngredientMapBuilder.append({"recipe_item_id": recipeItemId, "ingredient_id": j["ingredient_id"]})
                    recipeItemId += 1
                while text != "==directions==":
                    text = next(recipesFile).strip()
                while len(text) == 0:
                    text = next(recipesFile).strip()
                while text[0] != "#":
                    text = next(recipesFile).strip()
                    while len(text) == 0:
                        text = next(recipesFile).strip()
                    if text == "==end==":
                        break
                while len(text) < 3:
                    text = next(recipesFile).strip()
                recipeNumber = 1
                while text[0] == "#" or text[0:3] == "===":
                    # TODO get sub directions later
                    if text[0:3] == "===":
                        text = next(recipesFile).strip()
                        while len(text) < 3:
                            text = next(recipesFile).strip()
                    else:
                        hasDirections = True
                        if recipeNumber == 1:
                            directions = "<p>" + text[2:] + "</p>"
                        elif recipeNumber == 2:
                            directions = directions[0:3] + "1. " + directions[3:]
                            directions = directions + "<p>" + str(recipeNumber) + '. ' + text[2:] + "</p>"
                        else:
                            directions = directions + "<p>" + str(recipeNumber) + '. ' + text[2:] + "</p>"
                        text = next(recipesFile).strip()
                        recipeNumber += 1
                        while len(text) < 3:
                            text = next(recipesFile).strip()
                if hasDirections:
                    while text != "==end==":
                        if re.search(r'\[\[[Cc]ategory\:[a-zA-Z]+ [Rr]ecipes\]\]', text):
                            cats = re.findall(r'\[\[[Cc]ategory\:[a-zA-Z]+ [Rr]ecipes\]\]', text)
                            for cat in cats:
                                c = cat.lower().replace('[[category:', '').replace(' recipes]]', '')
                                if c in categoriesIds:
                                    categoryMapBuilder.append({"recipe_id": recipeId, "category_id": categoriesIds[c]})
                        text = next(recipesFile).strip()
                    recipeBuilder.append({"id": recipeId,
                                              "title": title,
                                              "directions": directions.replace('[[', '<font color="#FF4081">').replace(']]', '</font>') + "</ol></p>"})
                else:
                    while text != "==end==":
                        text = next(recipesFile).strip()
                    recipeBuilder.append({"id": recipeId,
                                              "title": "",
                                              "directions": ""})

                recipeId += 1
except(StopIteration):
    print("Dont worry")
recipesFile.close()
# ingredientsFile.close()

with open('category.json', 'w') as fp:
    json.dump(categoryBuilder, fp, sort_keys=True, indent=2, ensure_ascii=False)

with open('recipe.json', 'w') as fp:
    json.dump(recipeBuilder, fp, sort_keys=True, indent=2, ensure_ascii=False)

with open('category_map.json', 'w') as fp:
    json.dump(categoryMapBuilder, fp, sort_keys=True, indent=2, ensure_ascii=False)

with open('ingredient.json', 'w') as fp:
    json.dump(ingredientBuilder, fp, sort_keys=True, indent=2, ensure_ascii=False)

with open('recipe_ingredient.json', 'w') as fp:
    json.dump(recipeItemBuilder, fp, sort_keys=True, indent=2, ensure_ascii=False)

with open('ingredient_map.json', 'w') as fp:
    json.dump(itemIngredientMapBuilder, fp, sort_keys=True, indent=2, ensure_ascii=False)
