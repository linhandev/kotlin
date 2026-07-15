// LANGUAGE: +MultiPlatformProjects
// IGNORE_BACKEND_K2: ANY

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 127 -> sentence 127
 * NUMBER: 4
 * DESCRIPTION: EXPECT token in expect extension function declaration
 */

// MODULE: common
// FILE: common.kt

expect fun String.format127(): String

// MODULE: platform()()(common)
// FILE: platform.kt

actual fun String.format127(): String = this

// TESTCASE NUMBER: 1
fun box(): String = "OK".format127()
