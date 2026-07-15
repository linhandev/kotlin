#!/usr/bin/env kotlin -script

// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, whitespace-and-comments -> paragraph 3 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Shebang line with arguments; code after shebang with arguments compiles and runs
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val command = "kotlin"
    val flag = "-script"
    return if ("$command $flag".endsWith(flag)) "OK" else "NOK"
}
