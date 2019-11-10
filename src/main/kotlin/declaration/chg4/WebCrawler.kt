package declaration.chg4

interface WebCrawler {
    /**
     * The original file contains an ok button, with id: "make-everything-ok-button".
     * The new file path is a modified html which contains the ok button,
     * but not neccessarily with the same id.
     * Find the ok button in the original file,
     * then find the tag that shares the most attributes in the new file.
     * Finally, returns the path of the tag.
     */
    fun findOkButton(originalFilePath: String,
                     newFilePath: String
                     ): String

    /**
     * Same as above, but receives the tag id that we should look for in the orignial file.
     */
    fun findOkButton(originalFilePath: String,
                     newFilePath: String,
                     okbuttonId: String
                     ): String
}