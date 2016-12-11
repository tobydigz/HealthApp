package com.digzdigital.healthapp.model.data;

import java.util.ArrayList;

/**
 * Created by Digz on 10/12/2016.
 */

public class CreateFoodDbObjects {

    private ArrayList<Food> foods = new ArrayList<>();
    private Food food;
    private int[] foodType = {0, 1, 2, 3};
    private String[] foodBreakfast1 = {
            "Porridge made with milk flavoured with a pinch of cinnamon and a tbsp apple puree\nGlass of apple juice",
            "Greek yoghurt and ginger with chopped fresh fruit (mango, peach or nectarine) served on scotch pancakes\nFruit smoothie",
            "Bran flakes with semi-skimmed milk and sliced banana\nGlass of cranberry juice",
            "Porridge made with milk flavoured with a tbsp of tinned berries in fruit juice\nGreen or herbal tea",
            "Wholegrain toast spread with peanut butter\nYoghurt drink",
            "Greek yoghurt and ginger mixed with tbsp chopped dried fruit (apricots, figs or dates) and tbsp muesli\nGlass of orange juice",
            "Scrambled eggs on toasted bagel\nYoghurt drink"
    };
    private String[] foodBreakfast2 = {
            "Porridge made with milk flavoured with a pinch of cinnamon and a tbsp apple puree\nApple juice",
            "Pot of plain fromage frais mixed with chopped fresh fruit (mango, peach or nectarine) and a tbsp flaked almonds served on scotch pancakes\nPapaya smoothie",
            "Wheat bisk cereal with milk with mashed/sliced banana\nPapaya smoothie",
            "Porridge made with milk flavoured with a tbsp of berry compote\nHerbal tea",
            "Wholegrain toast spread with peanut butter\nYoghurt drink",
            "Pot of Greek yoghurt mixed with tbsp chopped dried fruit, flaked almonds and tbsp muesli (make the night before and keep in the fridge to soften)\nOrange juice",
            "Scrambled eggs on toasted bagel with spread\nYoghurt drink"
    };
    private String[] foodBreakfast3 = {
            "Porridge made with milk flavoured with a pinch of cinnamon and a tbsp apple puree\nApple juice",
            "Pot of plain fromage frais mixed with chopped fresh fruit (mango, peach or nectarine) and a tbsp flaked almonds served on scotch pancakes\nPapaya smoothie",
            "Wheat bisk cereal with milk with mashed/sliced banana\nPapaya smoothie",
            "Porridge made with milk flavoured with a tbsp of berry compote\nHerbal tea",
            "Wholegrain toast spread with peanut butter\nYoghurt drink",
            "Pot of Greek yoghurt mixed with tbsp chopped dried fruit, flaked almonds and tbsp muesli (make the night before and keep in the fridge to soften)\nOrange juice",
            "Scrambled eggs on toasted bagel with spread\nYoghurt drink"
    };

    private String[] foodLunch1 = {
            "Smoked chicken and avocado salad\nBanana",
            "Baked potato and cottage cheese\nSmall bunch of grapes",
            "Broccoli and pea soup with a crusty roll\nApple",
            "Feta salad couscous\nPapaya",
            "Cranberry and soft cheese wrap with watercress\nSlice of melon",
            "Salmon and watercress salad\nKiwi fruit",
            "Roast chicken with roast potatoes, broccoli and carrots\nApple and pear crumble"
    };
    private String[] foodLunch2 = {
            "Super salad\nChopped papaya",
            "Pistachio chicken with chopped mixed salad or smoked chicken and avocado salad\nChopped pineapple",
            "Watercress and celeriac soup with wholegrain toast and spread",
            "Sardines (good source of omega 3, calcium and vitamin D) on toast",
            "Ciabatta with halloumi, basil and sundried tomatoes\nOrange",
            "Cheesy baked beans in a baked potato",
            "Sweet apple lamb couscous and broccoli"
    };
    private String[] foodLunch3 = {
            "Couscous egg salad with pine nuts and currants\nSatsuma",
            "Roast beef and rocket baguette\nSmall bunch of grapes",
            "Beetroot soup\nMelon",
            "Pitta with lamb’s lettuce, gruyere and grapes\nSliced mango",
            "Salmon and watercress salad\nChopped apple",
            "Toasted ham and cheese wholemeal sandwich\nPear",
            "Roast lamb with roast potatoes, carrots and green beans\nSultana rice pudding"
    };
    private String[] foodDinner1 = {
            "Chicken cacciatore with brown rice",
            "Beef and black eye bean casserole",
            "Healthier sausage and apple casserole",
            "Creamy haddock and salmon pie with asparagus",
            "Lamb chops with new potatoes, broccoli and peas",
            "Pasta puttanesca with low-fat garlic bread",
            "Tofu and butternut squash flan"
    };
    private String[] foodDinner2 = {
            "Chicken and mushroom risotto\nSide salad",
            "Salmon with pine nuts, broccoli and sweet potato mash",
            "Spaghetti carbonara\nWilted spinach",
            "Shepherd’s pie with carrots",
            "Kedgeree",
            "Stir fry chicken with spring greens and noodles",
            "Mushroom and celery pasta bake"
    };
    private String[] foodDinner3 = {
            "Smoked mackerel and spinach pasta",
            "Creamy chickpea curry",
            "Chicken risotto",
            "Creamy haddock and salmon pie with green beans",
            "Lamb chops with sweet potato wedges and mange tout",
            "Beef lasagne made with ragu sauce with a mixed side salad",
            "Spinach and cheese quiche"
    };
    private String[] foodSnackA1 = {
            "Yoghurt drink\nOrange",
            "Slice of fruited malt loaf",
            "Sultana scotch pancake",
            "Pot of low-fat yoghurt",
            "Rice pot",
            "Small roll with peanut butter",
            "Banana"
    };
    private String[] foodSnackA2 = {
            "Handful each of dried apricots and almonds",
            "Sesame seed bar (good source of iron too)",
            "Pot of fromage frais",
            "Fruit scone with spread",
            "Banana",
            "Apple and bran muffin\n",
            "Apple and bran muffin\n"
    };
    private String[] foodSnackA3 = {
            "Small roll with peanut butter",
            "Muffin with a slice of edam",
            "Melon with blueberries and yoghurt",
            "Slice of fruited malt loaf with spread",
            "2 handfuls of walnuts and dried fruit",
            "Rice pot",
            "Strawberry milkshake"
    };
    private String[] foodSnackB1 = {
            "Small fruit or cheese scone",
            "Handful of dried apricots",
            "Carrot sticks and hummus dip",
            "Slice of banana bread",
            "Breadsticks with low- fat soft cheese dip",
            "2 fig rolls",
            "1-2 handfuls of mixed nuts and dried fruit"
    };
    private String[] foodSnackB2 = {
            "Small fruit or cheese scone with spread",
            "Hummus with pitta",
            "Oaty cranberry and orange cookie",
            "Rye crackers with soft cheese",
            "Slice of gingerbread",
            "2 handfuls of walnuts and dried fruit",
            "Oaty cranberry and orange cookie"
    };
    private String[] foodSnackB3 = {
            "Carrot sticks with hummus",
            "Thick slice of banana bread",
            "2 rye crackers with sardine paste",
            "2-3 mini falafels",
            "Fruity flapjack",
            "Wholemeal toast with baked beans",
            "Cheese on toast"
    };
    private String[][] foodTrimester1 = {foodBreakfast1, foodLunch1, foodDinner1, foodSnackA1, foodSnackB1};
    private String[][] foodTrimester2 = {foodBreakfast2, foodLunch2, foodDinner2, foodSnackA2, foodSnackB2};
    private String[][] foodTrimester3 = {foodBreakfast3, foodLunch3, foodDinner3, foodSnackA3, foodSnackB3};

    public CreateFoodDbObjects() {

    }

    public ArrayList<Food> getFoods() {

        addTrimesterFoods(foodTrimester1, 0);
        addTrimesterFoods(foodTrimester2, 1);
        addTrimesterFoods(foodTrimester3, 2);
        return foods;
    }

    private void addTrimesterFoods(String[][] foodTrimester, int trimester) {

        for (int foodTypes = 0; foodTypes < foodTrimester.length; foodTypes++) {
            for (int foodDay = 0; foodDay < 7; foodDay++) {
                Food food = new Food(foodTrimester[foodTypes][foodDay], foodTypes, foodDay, trimester);
                foods.add(food);
            }
        }
    }

}
