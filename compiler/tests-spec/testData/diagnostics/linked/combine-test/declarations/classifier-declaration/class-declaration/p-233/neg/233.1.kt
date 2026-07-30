// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 233 -> sentence 233
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 233 -> sentence 233
 *                inheritance, overriding -> paragraph 233 -> sentence 233
 * NUMBER: 1
 * DESCRIPTION: accept parameter type that mismatches the interface type argument is not an override (NOTHING_TO_OVERRIDE) and leaves the member unimplemented (ABSTRACT_MEMBER_NOT_IMPLEMENTED); contrasts with p-232 matching Sink type arguments and with p-185 class-member parameter narrowing
 */

// TESTCASE NUMBER: 1
interface Sink<T> {
    fun accept(x: T)
}

<!ABSTRACT_MEMBER_NOT_IMPLEMENTED!>class BadStringSink<!> : Sink<String> {
    <!NOTHING_TO_OVERRIDE!>override<!> fun accept(x: Int) {}
}

// TESTCASE NUMBER: 2
<!ABSTRACT_MEMBER_NOT_IMPLEMENTED!>class BadIntSink<!> : Sink<Int> {
    <!NOTHING_TO_OVERRIDE!>override<!> fun accept(x: String) {}
}

// TESTCASE NUMBER: 3
<!ABSTRACT_MEMBER_NOT_IMPLEMENTED!>class BadBoolSink<!> : Sink<Boolean> {
    <!NOTHING_TO_OVERRIDE!>override<!> fun accept(x: Long) {}
}
