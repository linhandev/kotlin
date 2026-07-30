// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 6 -> sentence 6
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: mutable property reference Box::v infers function type (Box) -> Int as getter, verifying type inference
 * HELPERS: checkType
 */

class Box(var v: Int)

// TESTCASE NUMBER: 1
fun case1(b: Box) {
    val read: (Box) -> Int = Box::v
    checkSubtype<(Box) -> Int>(read)
}
