// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 56 -> sentence 56
 * NUMBER: 2
 * DESCRIPTION: FILE token in file annotation @file:Suppress
 */

@file:Suppress("UNUSED_VARIABLE")

val fileLevelUnused = 100

// TESTCASE NUMBER: 1
fun box(): String {
    if (fileLevelUnused == 100) return "OK"
    return "NOK"
}
