// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 217 -> sentence 217
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 217 -> sentence 217
 *                inheritance, overriding -> paragraph 217 -> sentence 217
 * NUMBER: 1
 * DESCRIPTION: same-named abstract vals with incompatible types from two interfaces cannot be resolved by a single override (PROPERTY_TYPE_MISMATCH_ON_OVERRIDE); covers primary-ctor Int/String choices and body Boolean/Double; contrasts with p-216 identical-type single override and with p-195 body-only Int clash
 */

// TESTCASE NUMBER: 1
interface LeftId {
    val id: Int
}

interface RightId {
    val id: String
}

class BadIdInt(override val id: <!PROPERTY_TYPE_MISMATCH_ON_OVERRIDE!>Int<!>) : LeftId, RightId

class BadIdString(override val id: <!PROPERTY_TYPE_MISMATCH_ON_OVERRIDE!>String<!>) : LeftId, RightId

// TESTCASE NUMBER: 2
interface LeftName {
    val name: String
}

interface RightName {
    val name: Int
}

class BadNameString(override val name: <!PROPERTY_TYPE_MISMATCH_ON_OVERRIDE!>String<!>) : LeftName, RightName

class BadNameInt(override val name: <!PROPERTY_TYPE_MISMATCH_ON_OVERRIDE!>Int<!>) : LeftName, RightName

// TESTCASE NUMBER: 3
interface LeftFlag {
    val flag: Boolean
}

interface RightFlag {
    val flag: Double
}

class BadFlagBool : LeftFlag, RightFlag {
    override val flag: <!PROPERTY_TYPE_MISMATCH_ON_OVERRIDE!>Boolean<!> = true
}

class BadFlagDouble : LeftFlag, RightFlag {
    override val flag: <!PROPERTY_TYPE_MISMATCH_ON_OVERRIDE!>Double<!> = 1.0
}
