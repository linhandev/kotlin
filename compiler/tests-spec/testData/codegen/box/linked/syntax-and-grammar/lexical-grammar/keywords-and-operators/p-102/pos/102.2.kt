// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 102 -> sentence 102
 * NUMBER: 2
 * DESCRIPTION: PUBLIC token in public function declaration
 */
public fun publicFn102(): String = "codegen-102-2"
// TESTCASE NUMBER: 1
fun box(): String = if (publicFn102() == "codegen-102-2") "OK" else "NOK"
