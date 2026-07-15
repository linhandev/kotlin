// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 61 -> sentence 61
 * NUMBER: 3
 * DESCRIPTION: RECEIVER token in bracket use-site @receiver:[Suppress] on extension function
 */

fun @receiver:[Suppress("UNUSED_PARAMETER")] String.bracket(): String = this

// TESTCASE NUMBER: 1
fun box(): String {
    return "OK".bracket()
}
