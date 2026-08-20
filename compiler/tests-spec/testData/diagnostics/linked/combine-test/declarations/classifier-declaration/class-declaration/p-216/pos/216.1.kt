// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 216 -> sentence 216
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 216 -> sentence 216
 *                inheritance, overriding -> paragraph 216 -> sentence 216
 * NUMBER: 1
 * DESCRIPTION: type inference when a class declaration resolves two same-named abstract vals of identical type via a single override val
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface LeftId {
    val id: Int
}

interface RightId {
    val id: Int
}

class DualId(override val id: Int) : LeftId, RightId

fun case1() {
    val c = DualId(7)
    c checkType { check<DualId>() }
    checkSubtype<LeftId>(c)
    checkSubtype<RightId>(c)
    c.id checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
interface LeftName {
    val name: String
}

interface RightName {
    val name: String
}

class DualName(override val name: String) : LeftName, RightName

fun case2() {
    val c = DualName("Ann")
    c checkType { check<DualName>() }
    checkSubtype<LeftName>(c)
    checkSubtype<RightName>(c)
    c.name checkType { check<String>() }
}

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

fun case3() {
    val c = DualFlagBody()
    c checkType { check<DualFlagBody>() }
    checkSubtype<LeftFlag>(c)
    checkSubtype<RightFlag>(c)
    c.flag checkType { check<Boolean>() }
}
