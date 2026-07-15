// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, type-parameters, use-site-variance -> paragraph 5 -> sentence 5
 * NUMBER: 2
 * DESCRIPTION: Use-site variance cannot be used in supertype top-level type arguments
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Inv1<A>

interface Case1 : Inv1<<!PROJECTION_IN_IMMEDIATE_ARGUMENT_TO_SUPERTYPE!>out<!> Int>


// TESTCASE NUMBER: 2
interface Inv2<A>

interface Case2 : Inv2<<!PROJECTION_IN_IMMEDIATE_ARGUMENT_TO_SUPERTYPE!>in<!> Number>


// TESTCASE NUMBER: 3
interface Inv3<A>

interface Case3 : Inv3<<!PROJECTION_IN_IMMEDIATE_ARGUMENT_TO_SUPERTYPE!>*<!>>


// TESTCASE NUMBER: 4
interface Out4<out A>

interface Case4 : Out4<<!PROJECTION_IN_IMMEDIATE_ARGUMENT_TO_SUPERTYPE, REDUNDANT_PROJECTION!>out<!> Int>


// TESTCASE NUMBER: 5
interface In5<in A>

interface Case5 : In5<<!PROJECTION_IN_IMMEDIATE_ARGUMENT_TO_SUPERTYPE, REDUNDANT_PROJECTION!>in<!> Int>
