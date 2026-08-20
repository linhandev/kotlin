// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 22 -> sentence 22
 *                scopes-and-identifiers, identifiers-and-paths -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: FQCN reaches the other-package type when a same-named local class shadows the short name type inference
 * HELPERS: checkType
 */
// FILE: a.kt
package pkg56022.lib

class Node56022

// FILE: main.kt
package pkg56022.app

import checkSubtype

class Node56022

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<pkg56022.lib.Node56022>(pkg56022.lib.Node56022())
    checkSubtype<Node56022>(Node56022())
}
