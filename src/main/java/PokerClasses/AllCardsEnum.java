package PokerClasses;

public enum AllCardsEnum {
    d2(0),
    d3(1),
    d4(2),
    d5(3),
    d6(4),
    d7(5),
    d8(6),
    d9(7),
    dt(8),
    dj(9),
    dq(10),
    dk(11),
    da(12),
    c2(13),
    c3(14),
    c4(15),
    c5(16),
    c6(17),
    c7(18),
    c8(19),
    c9(20),
    ct(21),
    cj(22),
    cq(23),
    ck(24),
    ca(25),
    h2(26),
    h3(27),
    h4(28),
    h5(29),
    h6(30),
    h7(31),
    h8(32),
    h9(33),
    ht(34),
    hj(35),
    hq(36),
    hk(37),
    ha(38),
    s2(39),
    s3(40),
    s4(41),
    s5(42),
    s6(43),
    s7(44),
    s8(45),
    s9(46),
    st(47),
    sj(48),
    sq(49),
    sk(50),
    sa(51);





    private final int value;
    private AllCardsEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
