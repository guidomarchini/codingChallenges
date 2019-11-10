package declaration.chg5

interface TravelsSeller {
    /**
     * Planes have seats A B C (*) D E F G (*) H J K
     * (* is plane aisle)
     * You have to sell travels for a typical family (2 ADT 2 CHD).
     * You can assign seats in three different ways:
     * [D, E, F, G], [B, C, D, E] or [F, G, H, J]
     * You have to calculate the quantity of travels you can sell.
     *
     * The occupied seats is a string with every {row}{seat}, separated by a space.
     * Example: "1A 1C"
     */
    fun familyAssignations(plainRows: Int,
                           occupiedSeats: String
                           ): Int
}