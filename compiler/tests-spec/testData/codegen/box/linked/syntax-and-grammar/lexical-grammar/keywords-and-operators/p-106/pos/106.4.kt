// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 106 -> sentence 106
 * NUMBER: 4
 * DESCRIPTION: ENUM token in enum class implementing interface
 */
enum class Labeled106 : CharSequence {
    OK {
        override val length: Int = 2
        override fun get(index: Int): Char = "OK"[index]
        override fun subSequence(startIndex: Int, endIndex: Int): CharSequence =
            "OK".subSequence(startIndex, endIndex)
    }
}

// TESTCASE NUMBER: 1
fun box(): String = Labeled106.OK.toString()
