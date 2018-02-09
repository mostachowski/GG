package PokerRoom

import java.awt.Rectangle


interface ITablePositions6max {
    val WholeCard1: Rectangle
    val WholeCard2: Rectangle
    val WholeCard3: Rectangle
    val WholeCard4: Rectangle
    val WholeCard5: Rectangle

    val HeroCard1: Rectangle
    val HeroCard2: Rectangle

    val Pot: Rectangle
    val Position1Bet: Rectangle
    val Position2Bet: Rectangle
    val Position3Bet: Rectangle
    val Position4Bet: Rectangle
    val Position5Bet: Rectangle

    val Button1: Rectangle
    val Button2: Rectangle
    val Button3: Rectangle
    val Button4: Rectangle
    val Button5: Rectangle
    val Button6: Rectangle

    val FoldRect: Rectangle
    val CallCheckRect: Rectangle
    val RaiseRect: Rectangle

    val Width: Int
    val Height: Int


}