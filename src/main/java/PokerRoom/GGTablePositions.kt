package PokerRoom

import java.awt.Rectangle


class GGTablePositions6max
    : ITablePositions6max {
    override val HeroCard1: Rectangle = Rectangle(343, 458, 18, 40)
    override val HeroCard2: Rectangle = Rectangle(401, 458, 18, 40)
    override val WholeCard1: Rectangle = Rectangle(229, 243, 21, 46)
    override val WholeCard2: Rectangle = Rectangle(302, 243, 21, 46)
    override val WholeCard3: Rectangle = Rectangle(373, 243, 21, 46)
    override val WholeCard4: Rectangle = Rectangle(446, 243, 21, 46)
    override val WholeCard5: Rectangle = Rectangle(517, 243, 21, 46)
    override val Pot: Rectangle= Rectangle(324,351,155,22)
    override val Position1Bet = Rectangle(139,407,150,20)
    override val Position2Bet = Rectangle(130,235,150,20)
    override val Position3Bet = Rectangle(335,190,150,20)
    override val Position4Bet = Rectangle(562,235,150,20)
    override val Position5Bet = Rectangle(560,407,150,20)


    override val Button1 = Rectangle(325,420,30,19)
    override val Button2 = Rectangle(180,188,30,19)
    override val Button3 = Rectangle(450,170,30,19)
    override val Button4 = Rectangle(670,243,30,19)
    override val Button5 = Rectangle(590,415,30,19)
    override val Button6 = Rectangle(322,420,30,19)

    override val FoldRect = Rectangle(492,543,96,48)
    override val CallCheckRect = Rectangle(593,543,96,48)
    override val RaiseRect = Rectangle(693,543,96,48)





}