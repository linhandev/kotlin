// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: declarations, property-declaration -> paragraph 11 -> sentence 11
 *                declarations, function-declaration -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: implementing class inherits interface var with custom getter/setter bodies and no backing field
 */

// TESTCASE NUMBER: 1
interface AccessorsOnly {
    var n: Int
        get() = 1
        set(_) {}
}

class InheritAccessors : AccessorsOnly

class AnotherInherit : AccessorsOnly

fun box(): String {
    val c = InheritAccessors()
    if (c.n != 1) return "NOK: default-get"
    c.n = 99
    if (c.n != 1) return "NOK: set-no-backing-field"
    if (AnotherInherit().n != 1) return "NOK: another-implementor"
    val asIface: AccessorsOnly = InheritAccessors()
    if (asIface.n != 1) return "NOK: via-interface"
    asIface.n = 7
    if (asIface.n != 1) return "NOK: via-interface-set"
    return "OK"
}
