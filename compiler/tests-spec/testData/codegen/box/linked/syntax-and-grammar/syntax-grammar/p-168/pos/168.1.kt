// WITH_STDLIB

// LANGUAGE: +MultiPlatformProjects

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 168 -> sentence 168
 * NUMBER: 1
 * DESCRIPTION: platformModifier actual function declaration keyword
 */

// TESTCASE NUMBER: 1
fun platformFun(): String = "codegen-168-2"
fun box(): String = if (platformFun() == "codegen-168-2") "OK" else "NOK"
