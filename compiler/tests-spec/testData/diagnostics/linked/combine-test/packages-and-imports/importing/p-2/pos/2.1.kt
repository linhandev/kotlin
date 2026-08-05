// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 2 -> sentence 2
 *                packages-and-imports, modules -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: FQCN can reference a declaration from another package without import in the same module type inference
 * HELPERS: checkType
 */
// FILE: a.kt
package pkg56002.a

class Box56002

// FILE: main.kt
package pkg56002.b

import checkSubtype
// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<pkg56002.a.Box56002>(pkg56002.a.Box56002())
}
