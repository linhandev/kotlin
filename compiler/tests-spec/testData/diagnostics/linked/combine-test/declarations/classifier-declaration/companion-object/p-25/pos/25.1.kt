// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: anonymous object in companion exposes members via an interface type
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface HasX {
    val x: Int
}

class Box {
    companion object {
        val instance = object : HasX {
            override val x = 42
        }
    }
}

fun case_1() {
    checkSubtype<Int>(Box.instance.x)
}
