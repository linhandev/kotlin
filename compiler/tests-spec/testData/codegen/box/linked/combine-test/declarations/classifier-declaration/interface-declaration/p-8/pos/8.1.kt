// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: declarations, property-declaration -> paragraph 8 -> sentence 8
 *                declarations, function-declaration -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: interface val with custom getter body is inherited as default property implementation
 */

// TESTCASE NUMBER: 1
interface Sized {
    val size: Int get() = 0
}

class Box : Sized

class PairBox : Sized {
    // second implementor without override still gets default getter
}

fun box(): String {
    if (Box().size != 0) return "NOK: default-getter"
    if (PairBox().size != 0) return "NOK: another-implementor"
    val asIface: Sized = Box()
    if (asIface.size != 0) return "NOK: via-interface"
    return "OK"
}
