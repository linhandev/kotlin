#!/usr/bin/env kotlin

// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, whitespace-and-comments -> paragraph 3 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Standard shebang line at the beginning of file; code after shebang compiles and runs
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val afterShebang = 10 + 5
    return if (afterShebang == 15) "OK" else "NOK"
}
