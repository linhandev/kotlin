// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 28 -> sentence 28
 *                packages-and-imports, modules -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: Java interop types from java.util can be imported and used type inference
 * HELPERS: checkType
 */
import java.util.ArrayList

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<ArrayList<Int>>(ArrayList<Int>())
    checkSubtype<Int>(ArrayList<Int>().apply { add(1) }.size)
}
