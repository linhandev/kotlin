// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 216 -> sentence 216
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 216 -> sentence 216
 *                inheritance, overriding -> paragraph 216 -> sentence 216
 * NUMBER: 1
 * DESCRIPTION: same-named abstract vals with identical types from two interfaces are resolved by a single override val in the class declaration (primary ctor or body); contrasts with p-214 overload-by-params and with next-point conflicting property types
 */

// TESTCASE NUMBER: 1
interface LeftId {
    val id: Int
}

interface RightId {
    val id: Int
}

class DualId(override val id: Int) : LeftId, RightId

// TESTCASE NUMBER: 2
interface LeftName {
    val name: String
}

interface RightName {
    val name: String
}

class DualName(override val name: String) : LeftName, RightName

// TESTCASE NUMBER: 3
interface LeftFlag {
    val flag: Boolean
}

interface RightFlag {
    val flag: Boolean
}

class DualFlagBody : LeftFlag, RightFlag {
    override val flag: Boolean = true
}

fun box(): String {
    if (DualId(7).id != 7) return "NOK: id"
    val asLeftId: LeftId = DualId(3)
    if (asLeftId.id != 3) return "NOK: via-left-id"
    val asRightId: RightId = DualId(9)
    if (asRightId.id != 9) return "NOK: via-right-id"

    if (DualName("Ann").name != "Ann") return "NOK: name"
    val asLeftName: LeftName = DualName("Bob")
    if (asLeftName.name != "Bob") return "NOK: via-left-name"
    val asRightName: RightName = DualName("Cy")
    if (asRightName.name != "Cy") return "NOK: via-right-name"

    if (!DualFlagBody().flag) return "NOK: flag"
    val asLeftFlag: LeftFlag = DualFlagBody()
    if (!asLeftFlag.flag) return "NOK: via-left-flag"
    val asRightFlag: RightFlag = DualFlagBody()
    if (!asRightFlag.flag) return "NOK: via-right-flag"
    return "OK"
}
