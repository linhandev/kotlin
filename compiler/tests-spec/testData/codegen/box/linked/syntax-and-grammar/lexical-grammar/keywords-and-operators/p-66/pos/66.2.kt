// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 66 -> sentence 66
 * NUMBER: 2
 * DESCRIPTION: IMPORT token in import with alias as renamed binding
 */

import kotlin.text.uppercase as toUpper66

// TESTCASE NUMBER: 1
fun box(): String {
    return "ok".toUpper66()
}
