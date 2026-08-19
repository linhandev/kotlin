// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: declarations, property-declaration -> paragraph 11 -> sentence 11
 *                declarations, function-declaration -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: type inference for class inheriting interface var with custom getter/setter bodies and no backing field
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface AccessorsOnly {
    var n: Int
        get() = 1
        set(_) {}
}

class InheritAccessors : AccessorsOnly

fun case1() {
    val c = InheritAccessors()
    checkSubtype<InheritAccessors>(c)
    checkSubtype<AccessorsOnly>(c)
    checkSubtype<Int>(c.n)
    c.n = 99
    checkSubtype<Int>(c.n)
}
