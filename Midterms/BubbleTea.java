enum Topping{ BOBA, ALOE, AIYU };

abstract class Ingredient {
    int amount;

    abstract int getAmount();
}

class BlackTea extends Ingredient {
    BlackTea(int amount) {
        this.amount = amount;
    }

    int getAmount() {
        return amount;
    }
}

class MachaTea extends Ingredient {
    MachaTea(int amount) {
        this.amount = amount;
    }

    int getAmount() {
        return amount;
    }
}

class LycheeSyrup extends Ingredient {
    LycheeSyrup(int amount) {
        this.amount = amount;
    }

    int getAmount() {
        return amount;
    }
}

class SoyMilk extends Ingredient {
    SoyMilk(int amount) {
        this.amount = amount;
    }

    int getAmount() {
        return amount;
    }
}

class BubbleTea {
    private final int blackTea; 
    private final int machaTea;
    private final int lycheeSyrup;
    private final int soyMilk;
    private final List<Topping> topping;

    public BubbleTea(BlackTea blackTea, MachaTea machaTea, LycheeSyrup lycheeSyrup, SoyMilk soyMilk) {
        blackTea = blackTea.getAmount();
        machaTea = machaTea.getAmount();
        lycheeSyrup = lycheeSyrup.getAmount();
        soyMilk = soyMilk.getAmount();
        topping = null;
    }

    private BubbleTea(int blackTea, int machaTea, int lycheeSyrup, int soyMilk, List<Topping> topping) {
        this.blackTea = blackTea;
        this.machaTea = machaTea;
        this.lycheeSyrup = lycheeSyrup;
        this.soyMilk = soyMilk;
        this.topping = topping;
    }

    BubbleTea addTopping(List<Topping> topping) {
        if (topping.size() > 3) {
            return null;
        } 

        if (hasDupe(topping)) {
            return null;
        }

        return new BubbleTea(blackTea, machaTea, lycheeSyrup, soyMilk, topping);
    }

    public static void Main(String[] args) {
        BubbleTea lychee = new BubbleTea(new BlackTea(250), new MachaTea(0), new LycheeSyrup(250), new SoyMilk(0));
        // each ingredient amount is readable in the contructor

        List<Topping> topping = new List<>();
        topping.add(topping.BOBA);
        BubbleTea lycheeTopping = lychee.addTopping(topping);
        // adding toppings return a new BubbleTea
    }
}